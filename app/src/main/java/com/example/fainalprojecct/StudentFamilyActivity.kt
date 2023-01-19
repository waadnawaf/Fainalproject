package com.example.fainalprojecct

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fainalprojecct.databinding.ActivityStudentFamilyBinding
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessaging.getInstance
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log


//const val TOPIC = "/topics/myTopic"

class StudentFamilyActivity : AppCompatActivity() {

 private  lateinit var binding : ActivityStudentFamilyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentFamilyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendbtn.setOnClickListener {

            val email = binding.emailAdress.text.toString().trim()
            val subject = binding.subject.text.toString().trim()
            val message = binding.subject.text.toString().trim()

            val address = email.split(" ".toRegex()).toTypedArray()
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, address)
                putExtra(Intent.EXTRA_SUBJECT,subject)
                putExtra(Intent.EXTRA_TEXT,message)

            }

            startActivity(intent)
        }












        //FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)


       /* binding.clickBtn.setOnClickListener {
            val title= binding.name1.text.toString()
            val message= binding.reasonTv.text.toString()
            if (title.isNotEmpty() && message.isNotEmpty()){
                PushNotification(
                    NotificationData(title, message),
                     TOPIC
                ).also {
                    sendNotification(it)
                }
            }
        }*/
    }
    /*private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if (response.isSuccessful) {
                Log.d(TAG,"Response: ${Gson().toJson(response)}")
            }
            else{
                Log.e(TAG, response.errorBody().toString())
            }

        }catch (e :Exception){
            Log.e(TAG,e.toString())
        }
    }*/

}