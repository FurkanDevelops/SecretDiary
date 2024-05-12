package com.furkancolak.secretdiary.adapter


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.furkancolak.secretdiary.databinding.RecyclerRowBinding
import com.furkancolak.secretdiary.model.Diary
import com.furkancolak.secretdiary.view.Read
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.NonDisposableHandle.parent

class FeedRecyclerAdapter(private val postList: ArrayList<Diary>): RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder>() {
    // yukarıda yazan private val postList gösterilecek datalar

    class PostHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {
        // binding den sonra gelen recyclerView için yapılan layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val diary = postList[position]
        holder.binding.textViewTitle.text = postList.get(position).title
        holder.binding.textViewDate.text = postList.get(position).date
        holder.binding.card.setOnClickListener{
            // görüntüleme islemi

            val intent = Intent(holder.itemView.context,Read::class.java)
            intent.putExtra("email",diary.email)
            intent.putExtra("title",diary.title)
            intent.putExtra("date",diary.date)
            intent.putExtra("comment",diary.comment)
            startActivity(holder.itemView.context,intent,null)

        }
        holder.binding.button.setOnClickListener{
            // silme islemi
            Toast.makeText(holder.itemView.context,"Silme işlemi yapıldı",Toast.LENGTH_SHORT).show()
        }
    }

}
