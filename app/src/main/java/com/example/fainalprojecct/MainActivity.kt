package com.example.fainalprojecct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fainalprojecct.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signupPageBtn.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)}

        binding.logInPageBtn.setOnClickListener {
            val intent = Intent(this,LoginPageActivity::class.java)
            startActivity(intent)
        }
    }

  /*   private fun loginPageActivity(){
     val intent = Intent(this,LoginPageActivity::class.java)
     startActivity(intent)
}*/

   /* private fun signupPageActivity(){
        val intent = Intent(this,SignUpActivity::class.java)
        startActivity(intent)
    }*/



}