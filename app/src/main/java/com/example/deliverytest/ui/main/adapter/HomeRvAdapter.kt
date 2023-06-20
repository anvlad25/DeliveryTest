package com.example.deliverytest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repository.data.categories.CategoriesList
import com.example.repository.data.categories.Category
import com.example.deliverytest.databinding.ItemHomeBinding
import setImage

class HomeRvAdapter(private val itemClickListener: CategoryItemViewClickListener) : RecyclerView.Adapter<HomeRvAdapter.ViewHolder>() {
    private var category: List<Category> = mutableListOf()

    inner class ViewHolder(private val viewBinding: ItemHomeBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(category: Category) {
            viewBinding.textCategory.text = category.name
            viewBinding.imageCategory.setImage(category.image_url)
            itemView.setOnClickListener { itemClickListener.onItemViewClick(category) }
        }
    }

    fun setCategory(category: CategoriesList) {
        this.category = category.categoriesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = category.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(category[position])
    }
}