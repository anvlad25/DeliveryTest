package com.example.deliverytest.ui.bag.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deliverytest.R
import com.example.repository.data.room.BagDishesEntity
import com.example.deliverytest.databinding.ItemBagBinding
import setImage

class BagRvAdapter(private val bagItemViewClickListener: BagItemViewClickListener) :
    RecyclerView.Adapter<BagRvAdapter.ViewHolder>() {
    private var dishes: List<BagDishesEntity> = mutableListOf()

    inner class ViewHolder(private val viewBinding: ItemBagBinding, context: Context) :
        RecyclerView.ViewHolder(viewBinding.root) {
        private val symbolRubStr = context.getString(R.string.symbol_rub)
        private val symbolDotStr = context.getString(R.string.symbol_dot)
        private val gramStr = context.getString(R.string.gram)

        fun bind(dish: BagDishesEntity) {
            with(viewBinding) {
                textDishName.text = dish.name
                textCount.text = dish.cnt.toString()
                textDishPrice.text = "${dish.price} $symbolRubStr"
                textDishWeight.text = "$symbolDotStr ${dish.weight}$gramStr"
                imageDish.setImage(dish.image_url)

                imageCountPlus.setOnClickListener {
                    bagItemViewClickListener.onItemViewClickPlus(dish.user_id, dish.id)
                }
                imageCountMinus.setOnClickListener {
                    bagItemViewClickListener.onItemViewClickMinus(dish.user_id, dish.id)
                }
            }
        }
    }

    fun setDish(dish: List<BagDishesEntity>) {
        this.dishes = dish
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemBagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), parent.context
        )

    override fun getItemCount(): Int = dishes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dishes[position])
    }
}