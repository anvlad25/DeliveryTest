package com.example.deliverytest.ui.dishes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repository.data.dishes.Dish
import com.example.repository.data.dishes.DishesList
import com.example.deliverytest.databinding.ItemDishBinding
import setImage

class DishesRvAdapter(private val itemClickListener: DishItemViewClickListener) : RecyclerView.Adapter<DishesRvAdapter.ViewHolder>() {
    private var dishes: List<Dish> = mutableListOf()

    inner class ViewHolder(private val viewBinding: ItemDishBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(dish: Dish) {
            viewBinding.menuText.text = dish.name
            viewBinding.menuImage.setImage(dish.image_url)
            itemView.setOnClickListener { itemClickListener.onItemViewClick(dish) }
        }
    }

    fun setDish(dish: DishesList) {
        this.dishes = dish.dishesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemDishBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = dishes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dishes[position])
    }
}