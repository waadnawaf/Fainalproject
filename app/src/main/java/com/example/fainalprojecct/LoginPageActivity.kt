package com.example.fainalprojecct

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.fainalprojecct.databinding.ActivityLoginPageBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginPageActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginPageBinding
    private val db = Firebase.firestore
    lateinit var  userData : Users

    private var usersList : ArrayList<Users> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userData = Users("","","","")
        binding.logInBtn.setOnClickListener { loginUser() }
    }

      private fun loginUser():ArrayList<Users>{

          if (binding.logInUserNameEt.text.isNotEmpty() && binding.logInPasswordEt.text.isNotEmpty()){

                db.collection("Users")
                .get()
                .addOnSuccessListener { userResult ->
                    for (document in userResult){
                        val username = document.data["username"] as String
                        val password = document.data["password"] as String
                        val email = document.data["email"] as String
                        usersList.add(Users(document.id, username,email,password))
                    }
                    Log.d("hello", "$usersList")


                    checkUser()
                }

                    .addOnFailureListener {
                        Toast.makeText(this@LoginPageActivity,"Error getting documents.",Toast.LENGTH_SHORT).show()
                    }
            }else{
            Toast.makeText(this,"All field are required",Toast.LENGTH_SHORT).show()
        }
          return usersList

    }

    private fun checkUser(){

        val usernameEt = binding.logInUserNameEt.text.toString()
        val passwordEt = binding.logInPasswordEt.text.toString()
        var exist = false

        for (user in usersList){
            if (usernameEt == user.username){
                if (passwordEt == user.password){
                    userData = user
                    Log.d("jilll", "$userData ")
                    exist = true
                }
            }
        }
        if (exist){
            Toast.makeText(this,"Login successfully",Toast.LENGTH_SHORT).show()

            val intent = Intent(this@LoginPageActivity,StudentFamilyActivity::class.java)
            startActivity(intent)

        }else{
            Toast.makeText(this,"User doesn't exists",Toast.LENGTH_SHORT).show()
        }
    }
           private fun studentFamilyActivity(){
               val intent = Intent (this,StudentFamilyActivity::class.java)
               intent.putExtra("userData", userData)
               startActivity(intent)
           }
}