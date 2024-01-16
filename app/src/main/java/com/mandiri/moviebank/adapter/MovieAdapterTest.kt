//package com.mandiri.moviebank.adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.mandiri.moviebank.R
//import com.mandiri.moviebank.model.LastMovieEntity
//import com.mandiri.moviebank.model.MovieModel
//import kotlinx.android.synthetic.main.item_popular_movie.view.*
//import kotlinx.android.synthetic.main.item_recent_movie.view.*
//
//class MovieAdapter(
//    private val movies: List<MovieModel>,
//    private val itemClickListener: (Int) -> Unit
//) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    private val POPULAR_MOVIE_TYPE = 1
//    private val RECENT_MOVIE_TYPE = 2
//
//    override fun getItemCount(): Int = movies.size
//
//    override fun getItemViewType(position: Int): Int {
//        return if (position < movies.size / 2) POPULAR_MOVIE_TYPE else RECENT_MOVIE_TYPE
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        return when (viewType) {
//            POPULAR_MOVIE_TYPE -> {
//                val view = inflater.inflate(R.layout.item_popular_movie, parent, false)
//                PopularMovieViewHolder(view)
//            }
//            RECENT_MOVIE_TYPE -> {
//                val view = inflater.inflate(R.layout.item_recent_movie, parent, false)
//                RecentMovieViewHolder(view)
//            }
//            else -> throw IllegalArgumentException("Invalid view type")
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when (holder.itemViewType) {
//            POPULAR_MOVIE_TYPE -> (holder as PopularMovieViewHolder).bind(movies[position])
//            RECENT_MOVIE_TYPE -> (holder as RecentMovieViewHolder).bind(movies[position])
//        }
//    }
//
//    inner class PopularMovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        fun bind(movie: MovieModel) {
//            Glide.with(itemView.context)
//                .load("https://image.tmdb.org/t/p/w500/${movie.imageUrl}")
//                .into(itemView.iv)
//
//            itemView.setOnClickListener { itemClickListener.invoke(movie.id) }
//        }
//    }
//
//    inner  class  RecentMovieViewHolder(view: View):RecyclerView.ViewHolder(view){
//
//        fun  bind(data: LastMovieEntity){
//            itemView.apply {
//
//                tvMovieTitle.text = data.original_title
//                tvRating.text = "Rating: ${data.vote_average}"
//                Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + data.poster_path)
//                    .into(image_view)
//
//                setOnClickListener {
//                    listenerClick.invoke(data.id)
//                }
//            }
//        }
//
//    }
//}
