package com.furkancolak.secretdiary.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.furkancolak.secretdiary.R
import com.furkancolak.secretdiary.databinding.ActivityReadBinding
import com.furkancolak.secretdiary.databinding.RecyclerRowBinding
import com.furkancolak.secretdiary.model.Diary
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class Read : AppCompatActivity() {
    private lateinit var binding: ActivityReadBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var rowBinding: RecyclerRowBinding
    private lateinit var postList: ArrayList<Diary>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        rowBinding = RecyclerRowBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        postList = ArrayList<Diary>()

        firestore = Firebase.firestore
        auth = Firebase.auth
        val email = intent.getStringExtra("email")
        val title = intent.getStringExtra("title")
        val date = intent.getStringExtra("date")
        val comment = intent.getStringExtra("comment")

        binding.titleText.text = title
        binding.dateText.text = date
        binding.diaryText.text = comment

        getData()
    }

    fun getData() {
        fun getData() {
            firestore.collection("Post").addSnapshotListener { value, error ->
                if (error != null) {
                    Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()
                    return@addSnapshotListener
                }

                if (value != null && !value.isEmpty) {
                    val documents = value.documents
                    postList.clear()
                    for (document in documents) {
                        val date1 = document.get("date") as Timestamp?
                        val date = date1?.toDate()?.toString() ?: ""
                        val title = document.get("title") as String?
                        val comment = document.get("comment") as String?
                        val email = document.get("email") as String?

                        if (email == auth.currentUser?.email) {
                            postList.add(Diary(email, title, date, comment))
                        }
                    }
                }
            }
        }

    }
}