package com.furkancolak.secretdiary.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkancolak.secretdiary.adapter.FeedRecyclerAdapter
import com.furkancolak.secretdiary.databinding.ActivityOpenDiaryBinding
import com.furkancolak.secretdiary.model.Diary
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class OpenDiary : AppCompatActivity() {
    private lateinit var binding: ActivityOpenDiaryBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private lateinit var postArrayList: ArrayList<Diary>
    private lateinit var feedAdapter: FeedRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenDiaryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        db = Firebase.firestore
        storage = Firebase.storage
        postArrayList = ArrayList<Diary>()
        feedAdapter = FeedRecyclerAdapter(postArrayList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = feedAdapter
        getData()
        binding.diaryPlus.setOnClickListener{
            val intent = Intent(this@OpenDiary, WriteDairy::class.java)
            startActivity(intent)
        }
    }

    fun getData() {
        db.collection("Post").addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            if (value != null && !value.isEmpty) {
                val documents = value.documents
                postArrayList.clear() // Clear the existing list before adding new data
                for (document in documents) {
                    val email = document.get("email") as String?
                    val date1 = document.get("date") as Timestamp?
                    val date = date1?.toDate()?.toString() ?: "" // Use toDate() to convert Timestamp to Date object
                    val title = document.get("title") as String?
                    val comment = document.get("comment") as String?
                    val post = Diary(email, comment, date, title)
                    postArrayList.add(post)
                }
                feedAdapter.notifyDataSetChanged()
            }
        }
    }
}

