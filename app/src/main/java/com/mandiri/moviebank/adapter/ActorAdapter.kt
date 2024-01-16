package com.mandiri.moviebank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.moviebank.databinding.ItemImageBinding
import com.mandiri.moviebank.model.MovieDetailModel

class ActorAdapter : RecyclerView.Adapter<ActorAdapter.ViewHolder>() {
    private var data: MutableList<MovieDetailModel> = mutableListOf()
    private lateinit var itemClickListener: ((Int) -> Unit)

    fun itemClickListener(listener: ((Int) -> Unit)) {
        itemClickListener = listener
    }

    fun setDataActor(movie: MutableList<MovieDetailModel>) {
        this.data = movie
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieDetailModel) {
            itemView.apply {
                binding.ivImage.setImageResource(data.actor_image)
//                From response: cast
//                itemView.apply {
//                    Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+data.profile_path).into(image_view_actors)
//                    setOnClickListener {
//                        listenerClick.invoke(image_view_actors,"https://image.tmdb.org/t/p/w500/"+data.profile_path,absoluteAdapterPosition)
//                    }
//                }
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

