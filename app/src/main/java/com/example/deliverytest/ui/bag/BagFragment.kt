package com.example.deliverytest.ui.bag

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deliverytest.App
import com.example.deliverytest.Constant
import com.example.deliverytest.R
import com.example.deliverytest.databinding.FragmentBagBinding
import com.example.deliverytest.ui.bag.adapter.BagItemViewClickListener
import com.example.deliverytest.ui.bag.adapter.BagRvAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import setAvatar
import java.util.Calendar
import java.util.Locale

class BagFragment : Fragment() {
    private val viewModel: BagViewModel by viewModel()
    private var adapter: BagRvAdapter? = null
    private val binding: FragmentBagBinding by viewBinding()
    private val bagDishesDao: com.example.repository.data.room.BagDishesDao = get()
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var sumPay: Int = 0

    private val permissionResult = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { result ->
        if (!result) {
            Toast.makeText(
                context,
                getString(R.string.need_permissions_to_find_location),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val symbolRubStr = resources.getString(R.string.symbol_rub)

        initAdapter()
        checkPermission()
        getLocation()
        setUserAvatar(Constant.USER_AVATAR_URI, 50)
        setCurrentDate()

        viewModel.liveData.observe(viewLifecycleOwner) {
            adapter?.setDish(it)
            adapter?.notifyDataSetChanged()

            it.forEach { dish ->
                sumPay += dish.cnt * dish.price
            }
            binding.buttonPay.text = "Оплатить $sumPay $symbolRubStr"

        }
        getDataCoroutines()
    }

    private fun getDataCoroutines() {
        scope.launch {
            viewModel.getData()
        }
    }

    private fun initAdapter() {
        binding.rvBag.layoutManager = LinearLayoutManager(context)
        adapter = BagRvAdapter(object : BagItemViewClickListener {

            override fun onItemViewClickPlus(userId: Int, id: Int) {
                sumPay = 0
                scope.launch {
                    val cntDish = bagDishesDao.selectCntDish(userId, id)
                    bagDishesDao.updateCntDish(userId, id, cntDish + 1)
                }
            }

            override fun onItemViewClickMinus(userId: Int, id: Int) {
                sumPay = 0
                scope.launch {
                    val cntDish = bagDishesDao.selectCntDish(userId, id)
                    if (cntDish == 1) {
                        bagDishesDao.deleteDish(userId, id)
                    } else {
                        bagDishesDao.updateCntDish(userId, id, cntDish - 1)
                    }
                }
            }
        })
        binding.rvBag.adapter = adapter
    }

    private fun checkPermission() {
        permissionResult.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun getLocation() {
        val locationManager: LocationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        var locationByGps: Location? = null

        var cityName = Constant.NO_CITY

        val gpsLocationListener = LocationListener { location -> locationByGps = location }

        if (hasGps) {
            if (ActivityCompat.checkSelfPermission(
                    App.instance,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    App.instance,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000,
                    0F,
                    gpsLocationListener
                )
            }
        }

        val lastKnownLocationByGps =
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        lastKnownLocationByGps?.let {
            locationByGps = lastKnownLocationByGps
        }

        val latitude = locationByGps?.latitude
        val longitude = locationByGps?.longitude
        if (latitude == null || longitude == null) {
            binding.textCity.text = cityName
            return
        }

        val geocoder = Geocoder(App.instance, Locale.getDefault())
        val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
        if (addresses != null) {
            cityName = addresses[0].locality
            binding.textCity.text = cityName
        }
    }

    private fun setUserAvatar(uri: String, dp: Int) {
        binding.accountAvatar.setAvatar(uri, dp)
    }

    @SuppressLint("SimpleDateFormat")
    private fun setCurrentDate() {
        val sdf = SimpleDateFormat("dd MMMM, yyyy")
        val currentDate = sdf.format(Calendar.getInstance().time)
        binding.textDate.text = currentDate
    }

    companion object {
        fun newInstance() = BagFragment()
    }
}