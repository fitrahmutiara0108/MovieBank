package com.mandiri.moviebank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandiri.moviebank.R
import com.mandiri.moviebank.databinding.ItemMovieSearchBinding
import com.mandiri.moviebank.model.SearchMovieModel
import java.text.DecimalFormat

class SearchMovieAdapter : RecyclerView.Adapter<SearchMovieAdapter.ViewHolder>() {
    private var data: List<SearchMovieModel> = listOf()
    private lateinit var itemClickListener: ((Int) -> Unit)

    fun itemClickListener(listener: ((Int) -> Unit)) {
        itemClickListener = listener
    }

    fun setDataMovie(movie: List<SearchMovieModel>) {
        this.data = movie
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMovieSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SearchMovieModel) {
            itemView.apply {
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500/" + data.poster_path)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .into(binding.ivRecentMovie)
                binding.tvMovieTitle.text = data.title
                val decimalFormat = DecimalFormat("#.#")
                binding.tvRating.text = "Rating: ${decimalFormat.format(data.vote_average)}/10"
                setOnClickListener {
                    data.apply {
                        itemClickListener.invoke(data.id)
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieSearchBinding.inflate(
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

