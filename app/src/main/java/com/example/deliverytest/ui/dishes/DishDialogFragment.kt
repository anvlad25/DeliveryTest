package com.example.deliverytest.ui.dishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.deliverytest.R
import com.example.repository.data.dishes.Dish
import com.example.repository.data.room.BagDishesDao
import com.example.repository.data.room.BagDishesEntity
import com.example.deliverytest.databinding.FragmentDishDialogBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import setImage

class DishDialogFragment(private val dish: Dish) : DialogFragment() {
    private val binding: FragmentDishDialogBinding by viewBinding()
    private val bagDishesDao: BagDishesDao = get()
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dish_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initData(dish)
        binding.imageClose.setOnClickListener {
            dismiss()
        }
    }

    private fun initData(dish: Dish) {
        val symbolRubStr = resources.getString(R.string.symbol_rub)
        val symbolDotStr = resources.getString(R.string.symbol_dot)
        val gramStr = resources.getString(R.string.gram)
        with(binding) {
            imageDish.setImage(dish.image_url)
            textDishName.text = dish.name
            textDishPrice.text = "${dish.price} $symbolRubStr"
            textDishWeight.text = "$symbolDotStr ${dish.weight}$gramStr"
            textDishDesc.text = dish.description
        }

        binding.buttonDishAdd.setOnClickListener {
            scope.launch {
                if (bagDishesDao.selectCntDish(1, dish.id) == 0) {
                    bagDishesDao.insertDish(getBagDish(dish))
                } else {
                    val cntDish = bagDishesDao.selectCntDish(1, dish.id)
                    bagDishesDao.updateCntDish(1, dish.id, cntDish + 1)
                }
            }
        }
    }

    private fun getBagDish(dish: Dish): BagDishesEntity =
        with(dish) {
            BagDishesEntity(
                id,
                name,
                price,
                weight,
                image_url,
                1,
                1
            )
        }

    companion object {
        fun newInstance(dish: Dish): DishDialogFragment {
            return DishDialogFragment(dish)
        }
    }
}

