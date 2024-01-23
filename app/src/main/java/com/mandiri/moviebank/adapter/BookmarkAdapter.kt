package com.mandiri.moviebank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandiri.moviebank.R
import com.mandiri.moviebank.data.local.BookmarkEntity
import com.mandiri.moviebank.databinding.ItemMovieBinding

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    private var data: List<BookmarkEntity> = listOf()
    private lateinit var itemClickListener: ((Int) -> Unit)

    fun itemClickListener(listener: ((Int) -> Unit)) {
        itemClickListener = listener
    }

    fun setDataMovie(movie: List<BookmarkEntity>) {
        this.data = movie
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BookmarkEntity) {
            itemView.apply {
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500/"+data.poster_path)
                    .placeholder(R.drawable.ic_loading)
                    .into(binding.ivRecentMovie)
                binding.tvMovieTitle.text=data.original_title
                binding.tvRating.text="Rating: ${data.vote_average}"
                setOnClickListener {
                    itemClickListener.invoke(data.id)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(
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

