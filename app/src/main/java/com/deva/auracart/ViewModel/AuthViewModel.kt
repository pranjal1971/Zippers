package com.deva.auracart.ViewModel

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.deva.auracart.Data.Users
import com.deva.auracart.util.Utils
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit

class AuthViewModel:ViewModel() {
    private val _verificationId= MutableStateFlow<String?>(null)
    private val _otpSent= MutableStateFlow(false)
    val otpSent=_otpSent

    private val _sucessfullysignedin= MutableStateFlow(false)
    val sucessfullysignedin=_sucessfullysignedin

    private val _isCurrentUser= MutableStateFlow(false)
    val isCurrentUser=_isCurrentUser


    init {
        Utils.getAuthInstance().currentUser?.let {
            isCurrentUser.value=true
        }
    }

    fun sendOTP(userNumber: String,activity:Activity){

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(e: FirebaseException) {



            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken,
            ) {
             _verificationId.value=verificationId
                _otpSent.value=true
            }
        }


        val options = PhoneAuthOptions.newBuilder(Utils.getAuthInstance())
            .setPhoneNumber("+91$userNumber") // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

     fun signInWithPhoneAuthCredential(otp:String,userNumber: String,) {
        val credential = PhoneAuthProvider.getCredential(_verificationId.value.toString(), otp)
        Utils.getAuthInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //FirebaseDatabase.getInstance().getReference("AllUsers").child("Users").child(user.uid!!).setValue(user)
                 _sucessfullysignedin.value=true
                } else {

                }
            }
    }
}