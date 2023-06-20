package com.example.deliverytest.ui.dishes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repository.data.dishes.tegs.Teg
import com.example.repository.data.dishes.tegs.TegsList
import com.example.deliverytest.databinding.ItemTegBinding

class TegsRvAdapter : RecyclerView.Adapter<TegsRvAdapter.ViewHolder>() {
    private var tegs: List<Teg> = mutableListOf()

    inner class ViewHolder(private val viewBinding: ItemTegBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(teg: Teg) {
            viewBinding.tegToggle.text = teg.teg
            viewBinding.tegToggle.textOff = teg.teg
            viewBinding.tegToggle.textOn = teg.teg
        }
    }

    fun setTeg(teg: TegsList) {
        this.tegs = teg.tegsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemTegBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = tegs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tegs[position])
    }
}