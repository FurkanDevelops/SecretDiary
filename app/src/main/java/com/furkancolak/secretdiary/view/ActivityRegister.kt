package com.furkancolak.secretdiary.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.furkancolak.secretdiary.databinding.ActivityRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class ActivityRegister : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
    }
    fun loginRegister(view: View){
        val intent = Intent(this@ActivityRegister, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun registerRegister(view: View){
        var email = binding.usernameRegister.text.toString()
        var password = binding.passwordRegister.text.toString()
        if(email.isNotEmpty()&&password.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                Toast.makeText(this@ActivityRegister,"Kayıt başarılı",Toast.LENGTH_LONG).show()
                val intent = Intent(this@ActivityRegister, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@ActivityRegister,"Email ve şifre giriniz",Toast.LENGTH_LONG).show()
            }
        }
    }
}