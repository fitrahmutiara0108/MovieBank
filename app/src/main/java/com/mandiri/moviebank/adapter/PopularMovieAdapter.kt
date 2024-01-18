package com.mandiri.moviebank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandiri.moviebank.databinding.ItemPopularMovieBinding
import com.mandiri.moviebank.model.PopularMovieModel
import java.text.DecimalFormat

class PopularMovieAdapter: RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {
    private var data: List<PopularMovieModel> = listOf()
    private lateinit var itemClickListener :((Int)-> Unit)

    fun itemClickListener(listener:((Int)->Unit)){
        itemClickListener=listener
    }

    fun setDataPopularMovie(movie: List<PopularMovieModel>){
        this.data = movie
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieAdapter.ViewHolder {
        return  ViewHolder(ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PopularMovieAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemPopularMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: PopularMovieModel) {
            itemView.apply {
                binding.tvMovieTitle.text = data.originalTitle
                val decimalFormat = DecimalFormat("#.#")
                binding.tvRating.text = "Rating: ${decimalFormat.format(data.voteAverage)}/10"
                Glide.with(context).load("https://image.tmdb.org/t/p/w500/${data.posterPath}").into(binding.ivPopularMovie)
                setOnClickListener {
                    data.apply {
                        itemClickListener.invoke(data.id!!)
                    }
                }
            }

        }
    }
}

