package com.mandiri.moviebank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandiri.moviebank.R
import com.mandiri.moviebank.data.remote.network.response.Backdrop
import com.mandiri.moviebank.databinding.ItemImageBinding

class ImageAdapter(
    private var data: List<Backdrop>
) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    private lateinit var listenerClick:((ImageView,String,Int)->Unit)


    fun setItemClickListener(listener:((ImageView,String,Int)->Unit)){
        listenerClick=listener
    }

    fun setDataImage(lstImage: List<Backdrop>) {
        this.data = lstImage
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Backdrop) {
            itemView.apply {
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500/"+data.file_path)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .into(binding.ivImage)
                setOnClickListener {
                    listenerClick.invoke(binding.ivImage,"https://image.tmdb.org/t/p/w500/"+data.file_path,adapterPosition)
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

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

}

