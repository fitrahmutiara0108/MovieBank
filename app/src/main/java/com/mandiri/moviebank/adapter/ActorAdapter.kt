package com.mandiri.moviebank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandiri.moviebank.R
import com.mandiri.moviebank.data.remote.network.response.Cast
import com.mandiri.moviebank.databinding.ItemImageBinding

class ActorAdapter(
    private val data: List<Cast>
) : RecyclerView.Adapter<ActorAdapter.ViewHolder>() {
    private lateinit var itemClickListener:((ImageView,String,Int)->Unit)

    fun setItemClickListener(listener:((ImageView,String,Int)->Unit)){
        itemClickListener=listener
    }

    inner class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Cast) {
                itemView.apply {
                    Glide.with(context)
                        .load("https://image.tmdb.org/t/p/w500/"+data.profile_path)
                        .placeholder(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                        .into(binding.ivImage)
                    setOnClickListener {
                        itemClickListener.invoke(binding.ivImage,"https://image.tmdb.org/t/p/w500/"+data.profile_path, adapterPosition)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        val size = data.size
        return size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

}

