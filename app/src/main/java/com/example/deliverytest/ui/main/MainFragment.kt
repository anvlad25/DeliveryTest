package com.example.deliverytest.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager.*
import android.icu.text.SimpleDateFormat
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deliverytest.App
import com.example.deliverytest.Constant.NO_CITY
import com.example.deliverytest.Constant.USER_AVATAR_URI
import com.example.deliverytest.R
import com.example.repository.data.categories.Category
import com.example.deliverytest.databinding.FragmentMainBinding
import com.example.deliverytest.routing.DishesScreen
import com.example.deliverytest.ui.main.adapter.HomeRvAdapter
import com.example.deliverytest.ui.main.adapter.CategoryItemViewClickListener
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import setAvatar
import java.util.Calendar
import java.util.Locale


class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()
    private var adapter: HomeRvAdapter? = null
    private val binding: FragmentMainBinding by viewBinding()
    private val scope = CoroutineScope(Dispatchers.IO)
    private val cicerone: Cicerone<Router> = get()
    private val router = cicerone.router

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
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()
        checkPermission()
        getLocation()
        setUserAvatar(USER_AVATAR_URI, 50)
        setCurrentDate()

        viewModel.liveData.observe(viewLifecycleOwner) {
            adapter?.setCategory(it)
            adapter?.notifyDataSetChanged()
        }

        getDataCoroutines()
    }

    private fun initAdapter() {
        binding.rvHome.layoutManager = LinearLayoutManager(context)
        adapter = HomeRvAdapter(object : CategoryItemViewClickListener {
            override fun onItemViewClick(category: Category) {
                router.navigateTo(DishesScreen(category))
            }

        })
        binding.rvHome.adapter = adapter
    }

    private fun checkPermission() {
        permissionResult.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun getLocation() {
        val locationManager: LocationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        var locationByGps: Location? = null

        var cityName = NO_CITY

        val gpsLocationListener = LocationListener { location -> locationByGps = location }

        if (hasGps) {
            if (ActivityCompat.checkSelfPermission(
                    App.instance,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    App.instance,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PERMISSION_GRANTED
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

    @SuppressLint("SimpleDateFormat")
    private fun setCurrentDate() {
        val sdf = SimpleDateFormat("dd MMMM, yyyy")
        val currentDate = sdf.format(Calendar.getInstance().time)
        binding.textDate.text = currentDate
    }

    private fun setUserAvatar(uri: String, dp: Int) {
        binding.accountAvatar.setAvatar(uri, dp)
    }

    private fun getDataCoroutines() {
        scope.launch {
            viewModel.getData()
        }
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}