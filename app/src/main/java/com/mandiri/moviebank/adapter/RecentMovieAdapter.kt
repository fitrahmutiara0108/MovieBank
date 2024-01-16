package com.mandiri.moviebank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.moviebank.databinding.ItemMovieBinding
import com.mandiri.moviebank.model.RecentMovieModel

class RecentMovieAdapter : RecyclerView.Adapter<RecentMovieAdapter.ViewHolder>() {
    private var data: MutableList<RecentMovieModel> = mutableListOf()
    private lateinit var itemClickListener: ((Int) -> Unit)

    fun itemClickListener(listener: ((Int) -> Unit)) {
        itemClickListener = listener
    }

    fun setDataRecentMovie(movie: MutableList<RecentMovieModel>) {
        this.data = movie
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RecentMovieModel) {
            itemView.apply {
                binding.tvMovieTitle.text = data.original_title.toString()
                binding.tvRating.text = data.vote_average.toString()
                binding.ivRecentMovie.setImageResource(data.image)
                binding.ivRecentMovie.setOnClickListener {
                    itemClickListener.invoke(data.id)
                }
//                binding.tvMovieTitle.text = data.original_title
//                binding.tvRating.text = "Rating: ${data.vote_average}/10"
//                Glide.with(context).load("https://image.tmdb.org/t/p/w500/${data.poster_path}").into(binding.ivRecentMovie)
//                setOnClickListener {
//                    data.apply {
//                        itemClickListener.invoke(id)
//                    }
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

