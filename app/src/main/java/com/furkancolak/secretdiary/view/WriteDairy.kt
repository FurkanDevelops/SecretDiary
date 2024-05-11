package com.furkancolak.secretdiary.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.furkancolak.secretdiary.R
import com.furkancolak.secretdiary.databinding.ActivityWriteDairyBinding
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class WriteDairy : AppCompatActivity() {
    private lateinit var binding: ActivityWriteDairyBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteDairyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth= Firebase.auth
        firestore = Firebase.firestore

        binding.saveButton.setOnClickListener{
            var date = Timestamp.now()
            var title = binding.titleText.text.toString()
            var diaryText = binding.diaryText.text.toString()
            val postMap = hashMapOf<String,Any>()
            postMap.put("comment",title)
            postMap.put("date",date)
            postMap.put("email",auth.currentUser!!.email!!)
            postMap.put("title",diaryText)
            firestore.collection("Post").add(postMap).addOnSuccessListener {
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@WriteDairy,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }
}