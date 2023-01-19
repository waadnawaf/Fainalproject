package com.example.fainalprojecct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.fainalprojecct.databinding.ActivitySignupPageBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

 class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupPageBinding

    var db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  db=Firebase.fire
        binding.signupBtn.setOnClickListener {signupUser()}
    }

    private  fun signupUser(){
        if (binding.signUpUserNameEt.text.isNotEmpty() && binding.signUpEmailEt.text.isNotEmpty() && binding.signUpPasswordEt.text.isNotEmpty()){
            val username = binding.signUpUserNameEt.text.toString()
            val email = binding.signUpEmailEt.text.toString()
            val password = binding.signUpPasswordEt.text.toString()


            val isExist = checkUsername(username)
            if (isExist){
                Toast.makeText(this,"This username already exists",Toast.LENGTH_SHORT).show()
            }
            else{
                val userObj = UserFb (username,email,password)
                CoroutineScope(Dispatchers.IO).launch {
                    db.collection("Users")
                        .add(userObj)
                        .addOnSuccessListener {
                            Toast.makeText(this@SignUpActivity, "Sign up successfully",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@SignUpActivity, StudentFamilyActivity::class.java)
                            startActivity(intent)
                        }
                        .addOnFailureListener { e ->
                            Log.d("Tag","Error adding document" ,e)
                        }
                    }

            }
        } else{
            Toast.makeText(this,"All field are required", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkUsername (username : String):Boolean{
        var exit = false
        db.collection("users")
            .get()
            .addOnSuccessListener { usersResult ->
                for (document in usersResult){
                    val getUsername = document.data["username"].toString()
                    if (username == getUsername){
                        exit = true
                    }
                }
            }
              .addOnFailureListener {
                  Toast.makeText(this@SignUpActivity,"Error getting documents.",Toast.LENGTH_SHORT).show()
              }

            return exit

    }
}