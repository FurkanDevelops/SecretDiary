package com.furkancolak.secretdiary.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.furkancolak.secretdiary.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
    }
    fun login(view: View){
        var email = binding.username.text.toString()
        var password = binding.password.text.toString()
        if(email.isNotEmpty()&&password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent = Intent(this@MainActivity, OpenDiary::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,"Email ve şifre giriniz", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun register(view: View){
        val intent = Intent(this@MainActivity, ActivityRegister::class.java)
        startActivity(intent)
    }
}