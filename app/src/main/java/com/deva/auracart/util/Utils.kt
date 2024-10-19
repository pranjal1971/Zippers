package com.deva.auracart.util

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.deva.auracart.databinding.ProgressDilogueBinding
import com.google.firebase.auth.FirebaseAuth

object Utils {
    private var dialog:AlertDialog?=null

    fun showDialog(context: Context,message:String){
        val progress=ProgressDilogueBinding.inflate(LayoutInflater.from(context))
        progress.tvMessege.text=message
        dialog=AlertDialog.Builder(context).setView(progress.root).setCancelable(false).create()
        dialog!!.show()
    }

    fun hideDialog(){
        dialog?.dismiss()
    }


    private var firebaseAuthInstance: FirebaseAuth?=null
    fun getAuthInstance():FirebaseAuth{
        if(firebaseAuthInstance==null){
            firebaseAuthInstance= FirebaseAuth.getInstance()
        }
        return firebaseAuthInstance!!
    }

    fun getUserUid():String{
        return FirebaseAuth.getInstance().currentUser?.uid!!
    }
}