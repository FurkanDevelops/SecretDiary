package com.furkancolak.secretdiary.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furkancolak.secretdiary.databinding.RecyclerRowBinding
import com.furkancolak.secretdiary.model.Diary

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
        holder.binding.textViewTitle.text = postList.get(position).title
        holder.binding.textViewDate.text = postList.get(position).date
    }


}
