package com.deva.auracart.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.deva.auracart.Data.Category
import com.deva.auracart.databinding.CategoryItemBinding

class AdapterCategory (val categoryList: ArrayList<Category>):RecyclerView.Adapter<AdapterCategory.CategoryViewHolder>(){
    class CategoryViewHolder(val binding: CategoryItemBinding):ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return  CategoryViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
       return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category=categoryList[position]
        holder.binding.apply {
            imageView2.setImageResource(category.image)
            catItemName.text=category.tittle.toString()
        }
    }
}