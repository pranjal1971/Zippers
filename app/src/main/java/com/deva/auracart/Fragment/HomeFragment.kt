package com.deva.auracart.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.deva.auracart.Adapters.AdapterCategory
import com.deva.auracart.Constant
import com.deva.auracart.Data.Category

import com.deva.auracart.R
import com.deva.auracart.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
     private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    binding=FragmentHomeBinding.inflate(layoutInflater)
        activity?.window?.statusBarColor = resources.getColor(R.color.yellow)
        // Inflate the layout for this fragment
        setAllCategory()
        return binding.root
    }

    private fun setAllCategory() {
        val categoryList=ArrayList<Category>()
        for(i in 0 until Constant.allProductCatIcon.size){
          categoryList.add(Category(Constant.allProductCat[i],Constant.allProductCatIcon[i]))
        }
        binding.rcvCat.adapter=AdapterCategory(categoryList)
    }


}