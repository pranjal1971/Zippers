package com.deva.auracart.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.deva.auracart.FormActivity
import com.deva.auracart.R
import com.deva.auracart.ViewModel.AuthViewModel
import com.deva.auracart.databinding.FragmentOtpBinding
import com.deva.auracart.util.Utils
import kotlinx.coroutines.launch


class OtpFragment : Fragment() {
    private val viewModel:AuthViewModel by viewModels()
     private lateinit var binding: FragmentOtpBinding
     private lateinit var userNumber: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentOtpBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        getUserNumber()
        getOTP()
        onBackBtnpress()

        sendOTP()

        onloginbtnClick()
        return binding.root;
    }

    private fun onloginbtnClick() {
        binding.loginButton.setOnClickListener {
            Utils.showDialog(requireContext(),"Verifing you...")
            val otp=binding.otpEditTxt.text.toString()
            if(otp.isBlank() || otp.length !=6){
                Toast.makeText(requireContext(),"Enter valid OTP ",Toast.LENGTH_SHORT).show()
            }
            else{
                verifyOTP(otp)
            }
        }
    }

    private fun verifyOTP(otp: String) {
       // val user= Users(uid =Utils.getUserUid(),userNumber=userNumber,userAdress = null)
       viewModel.signInWithPhoneAuthCredential(otp, userNumber)

        lifecycleScope.launch {
            viewModel.sucessfullysignedin.collect{
                if(it){
                    Utils.hideDialog()
                    Toast.makeText(requireContext(),"Sucessfully verified",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(), FormActivity::class.java))
                    requireActivity().finish()
                }
            }
        }
    }

    private fun sendOTP() {
         Utils.showDialog(requireContext(),"Sending OTP...")

        viewModel.apply {
            sendOTP(userNumber,requireActivity())
            lifecycleScope.launch {
                otpSent.collect{
                    if(it==true){
                        Utils.hideDialog()
                        Toast.makeText(requireContext(),"OTP sent",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun onBackBtnpress() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_otpFragment_to_signUpFragment)
        }
    }

    private fun getUserNumber() {
        val bundle=arguments
        userNumber=bundle?.getString("number").toString()

        binding.userNoOtpScreen.text=userNumber
    }

    private fun getOTP() {
        binding.otpEditTxt.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(number: CharSequence?, start: Int, before: Int, count: Int) {
                    val len=number?.length
                    if(len==6){
                        binding.loginButton.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.yellow))
                    }
                    else{
                        binding.loginButton.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.lightblue))
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }
            }
        )
    }
}

