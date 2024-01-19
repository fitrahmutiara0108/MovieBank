package com.mandiri.moviebank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.moviebank.databinding.ActivityDetailMovieBinding
import com.mandiri.moviebank.model.MovieDetailModel

class MovieDetailAdapter(
    private var data: List<MovieDetailModel>
) : RecyclerView.Adapter<MovieDetailAdapter.ViewHolder>() {
    private lateinit var itemClickListener: ((Int) -> Unit)

    fun itemClickListener(listener: ((Int) -> Unit)) {
        itemClickListener = listener
    }

    fun setDataDetailMovie(movie: List<MovieDetailModel>) {
        this.data = movie
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ActivityDetailMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieDetailModel) {
            itemView.apply {
                binding.tvMovieTitle.text = data.original_title
                binding.tvDuration.text = data.runtime.toString()
                binding.tvBudget.text = data.budget.toString()
                binding.tvGenre.text = data.genres.toString()
                binding.tvCompanies.text = data.production_companies.toString()
                binding.tvDescription.text = data.overview
                binding.tvReleasedDate.text = data.release_date
                binding.tvRuntime.text = data.runtime.toString()

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
           ActivityDetailMovieBinding.inflate(
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

