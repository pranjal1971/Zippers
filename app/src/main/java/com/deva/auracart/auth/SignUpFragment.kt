package com.deva.auracart.auth

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.deva.auracart.R
import com.deva.auracart.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

   private lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSignUpBinding.inflate(layoutInflater)

        getUserNumber()

        onContinueClicked()
        return binding.root
    }

    private fun onContinueClicked() {
        binding.continueButton.setOnClickListener {
            val number=binding.userNoEditTxt.text.toString()
            if(number.isEmpty()  || number.length!=10){
                Toast.makeText(requireContext(), "Please Enter valid phone no", Toast.LENGTH_SHORT).show()
            }
            else{
                val bundle=Bundle()
                bundle.putString("number",number)
                findNavController().navigate(R.id.action_signUpFragment_to_otpFragment,bundle)
            }
        }
    }

    private fun getUserNumber() {
        binding.userNoEditTxt.addTextChangedListener(
            object :TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(number: CharSequence?, start: Int, before: Int, count: Int) {
                    val len=number?.length
                    if(len==10){
                        binding.continueButton.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.yellow))
                    }
                    else{
                        binding.continueButton.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.lightblue))
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }
            }
            )
    }


}