package com.deva.auracart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deva.auracart.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFormBinding.inflate(layoutInflater)
        binding.btnStudent.setOnClickListener {
            val intent = Intent(this, StudentFormActivity::class.java)
            val message = "Student" // Replace with your string data

            intent.putExtra("stu", message)
            startActivity(intent)
        }
        setContentView(binding.root)
    }
}