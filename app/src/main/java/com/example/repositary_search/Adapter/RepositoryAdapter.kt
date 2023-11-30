package com.example.repositary_search.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.repositary_search.R
import com.example.repositary_search.Retrofit.models.Item

class RepositoryAdapter: RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> (){

    class RepositoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       val Title: TextView = itemView.findViewById(R.id.tv_title)
       val thumbnail: ImageView = itemView.findViewById(R.id.image_logo)
       val cardView: ConstraintLayout = itemView.findViewById(R.id.cardView)
   }

    private val differCallback = object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.repository_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = differ.currentList[position]
        holder.Title.text = repository.full_name
        setOnItemClickListener {
            onItemClickListener?.let{ it(repository)}
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Item) -> Unit)? = null

    fun setOnItemClickListener(listener: (Item) -> Unit) {
        onItemClickListener = listener
    }
}