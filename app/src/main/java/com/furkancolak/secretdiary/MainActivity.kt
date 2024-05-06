package com.furkancolak.secretdiary

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,"Email ve şifre giriniz", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun register(view: View){
        val intent = Intent(this@MainActivity,ActivityRegister::class.java)
        startActivity(intent)
    }
}