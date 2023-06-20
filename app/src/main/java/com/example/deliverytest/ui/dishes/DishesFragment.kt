package com.example.deliverytest.ui.dishes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deliverytest.Constant
import com.example.deliverytest.R
import com.example.repository.data.categories.Category
import com.example.repository.data.dishes.Dish
import com.example.repository.data.dishes.DishesList
import com.example.repository.data.dishes.tegs.Teg
import com.example.repository.data.dishes.tegs.TegsList
import com.example.deliverytest.databinding.FragmentDishesBinding
import com.example.deliverytest.routing.MainScreen
import com.example.deliverytest.ui.dishes.adapter.DishItemViewClickListener
import com.example.deliverytest.ui.dishes.adapter.DishesRvAdapter
import com.example.deliverytest.ui.dishes.adapter.TegsRvAdapter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import setAvatar

class DishesFragment(private val category: Category) : Fragment() {
    private val viewModel: DishesViewModel by viewModel()
    private var adapterDishes: DishesRvAdapter? = null
    private var adapterTegs: TegsRvAdapter? = null
    private val binding: FragmentDishesBinding by viewBinding()
    private val scope = CoroutineScope(Dispatchers.IO)
    private val cicerone: Cicerone<Router> = get()
    private val router = cicerone.router

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dishes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUserAvatar(Constant.USER_AVATAR_URI, 50)
        setCategoryText()

        initAdapterDishes()
        initAdapterTegs()

        viewModel.liveData.observe(viewLifecycleOwner) {
            val dishTest: MutableList<Dish> = mutableListOf()

            /*it.dishesList.forEach {dish ->
                if (dish.tegs.indexOf("Салаты") > -1) dishTest.add(dish)
            }*/

            //adapterDishes?.setDish(DishesList(dishTest))
            adapterDishes?.setDish(it)
            adapterDishes?.notifyDataSetChanged()

            adapterTegs?.setTeg(getTegsList(it))
            adapterTegs?.notifyDataSetChanged()
        }

        getDataCoroutines()

        binding.backIcon.setOnClickListener {
            router.newRootScreen(MainScreen)
        }
    }

    private fun setCategoryText() {
        binding.textDishCategory.text = category.name
    }

    private fun getTegsList(dishesList: DishesList): TegsList {
        val tegsList: MutableList<Teg> = mutableListOf()

        dishesList.dishesList.forEach { dish ->
            dish.tegs.forEach { teg ->
                if (tegsList.indexOf(Teg(teg)) == -1) tegsList.add(
                    Teg(teg)
                )
            }
        }

        return TegsList(tegsList)
    }

    private fun getDataCoroutines() {
        scope.launch {
            viewModel.getData()
        }
    }

    private fun initAdapterDishes() {
        binding.rvDishes.layoutManager = GridLayoutManager(context, 3)
        adapterDishes = DishesRvAdapter(object : DishItemViewClickListener {
            override fun onItemViewClick(dish: Dish) {
                val dishDialogFragment = DishDialogFragment.newInstance(dish)
                dishDialogFragment.show(childFragmentManager, "dish")
            }

        })
        binding.rvDishes.adapter = adapterDishes
    }

    private fun initAdapterTegs() {
        binding.rvTegs.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
        adapterTegs = TegsRvAdapter()
        binding.rvTegs.adapter = adapterTegs
    }

    private fun setUserAvatar(uri: String, dp: Int) {
        binding.accountAvatarDishes.setAvatar(uri, dp)
    }

    companion object {
        fun newInstance(category: Category) = DishesFragment(category)
    }
}