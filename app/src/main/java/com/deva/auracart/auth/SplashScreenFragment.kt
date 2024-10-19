package com.deva.auracart.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.deva.auracart.FormActivity
import com.deva.auracart.R
import com.deva.auracart.ViewModel.AuthViewModel
import kotlinx.coroutines.launch


class SplashScreenFragment : Fragment() {

    private val viewModel:AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)


        // Change the color of the status bar
        activity?.window?.statusBarColor = resources.getColor(R.color.yellow)

        Handler(Looper.getMainLooper()).postDelayed({
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.splashScreenFragment, true) // Pop up the splash screen from the back stack
                .build()
            lifecycleScope.launch {
                viewModel.isCurrentUser.collect{
                    if(it){
                        startActivity(Intent(requireActivity(), FormActivity::class.java))
                        requireActivity().finish()
                    }
                    else{
                        findNavController().navigate(R.id.action_splashScreenFragment_to_signUpFragment, null, navOptions)
                    }

                    }                    }
                }
            , 3000) // Set your desired delay time in milliseconds


        return view
    }



}