package com.deva.auracart

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.deva.auracart.databinding.ActivityStudentFormBinding


class StudentFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentFormBinding
    private fun setSpinrer(selectedItem: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityStudentFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow)

        binding.radioButtonFemale.setOnClickListener {
            binding.radioButtonMale.isChecked = false
        }
        binding.radioButtonMale.setOnClickListener {
            binding.radioButtonFemale.isChecked = false
        }

        //setting up spiner
        val catlist= listOf("Dance","Music","Musical Instrument")
        val catAdapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,catlist)
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.StudentCatSpiner.adapter=catAdapter

        binding.StudentCatSpiner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    // Show a toast message with the selected item
                     setSpinrer(selectedItem)
                       binding.StudentShowTxt.text= "What do yo want to learn in $selectedItem"

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle the case when nothing is selected (optional)
                }
            }


        val position = intent.getStringExtra("stu")
        val name=binding.studentName.text.toString()



        setContentView(binding.root)

    }
}