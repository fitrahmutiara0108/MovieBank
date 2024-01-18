package com.mandiri.moviebank.presentation.movie

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mandiri.moviebank.adapter.ActorAdapter
import com.mandiri.moviebank.adapter.ImageAdapter
import com.mandiri.moviebank.data.network.response.Backdrop
import com.mandiri.moviebank.data.network.response.Cast
import com.mandiri.moviebank.databinding.ActivityDetailMovieBinding
import com.mandiri.moviebank.model.MovieDetailModel
import com.mandiri.moviebank.presentation.movie.viewmodel.MovieDetailViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.ln
import kotlin.math.pow

class MovieDetailActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var actorAdapter: ActorAdapter
    private lateinit var data: MovieDetailModel

    private val viewModel: MovieDetailViewModel by viewModels()

    private var movieId: Int? = 0

    companion object {
        lateinit var instance: MovieDetailActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        movieId = intent.getIntExtra("MOVIE_ID", -1)
//        viewModel.loadDataById(movieId ?: 0)

        setupView()
    }

    private fun setupView() {
        viewModel.setData(movieId ?: 0)
//        viewModel.loadAuthorsDataById(movieId ?: 0)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.fullMovie.observe(this, { movie ->
            data = movie
            setupViewMovieDetail(movie)
        })
        viewModel.authorsList.observe(this, { actors ->
            setupViewActor(actors)
        })
        viewModel.images.observe(this, { image ->
            setupViewImage(image)
        })

    }

    private fun setupViewActor(data: List<Cast>) {
        actorAdapter = ActorAdapter(data)
        binding.rvActors.adapter = actorAdapter
    }

    private fun setupViewImage(data: List<Backdrop>) {
        val imageAdapter = ImageAdapter(data)
        binding.rvMovieImages.adapter = imageAdapter
    }

    private fun setupViewMovieDetail(movie: MovieDetailModel) {
        binding.tvMovieTitle.text = data.title
        binding.tvDuration.text = "${data.runtime} minutes"
        if (binding.tvBudget != null) {
            binding.tvBudget.text = "Budget: ${numberFormatter(data.budget.toLong())}"
        } else {
            binding.tvBudget.visibility = View.GONE
        }
        val genre = StringBuilder()
        data.genres.forEachIndexed { index, g ->
            genre.append("${g.name}" + if (index != data.genres.size - 1) ", " else "")
        }
        binding.tvGenre.text = "Genres: $genre"
        val companies = StringBuilder()
        data.production_companies.forEachIndexed { index, prCompany ->
            companies.append("\n${prCompany.name}" + if (index != data.production_companies.size - 1) ", " else "")
        }
        binding.tvCompanies.text = "Production companies: $companies"
        binding.tvDescription.text = data.overview
        data.imdb_id?.let {
            binding.tvImdb.text = "IMDb: " + it.subSequence(2, data.imdb_id!!.length)
        }
        binding.tvReleasedDate.text = "Released: ${dataFormatter(data.release_date)}"
        binding.tvRuntime.text = "Runtime: ${data.runtime / 60} h ${data.runtime % 60} min"
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/${data.poster_path}")
            .into(binding.ivImage)
    }

    private fun numberFormatter(count: Long): String {
        if (count < 1000) return "" + count
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format(
            "%.1f %c",
            count / 1000.0.pow(exp.toDouble()),
            "kMGTPE"[exp - 1]
        )
    }

    private fun dataFormatter(data: String): String {
        val incomingDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val showedDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val date = incomingDateFormat.parse(data)
        return showedDateFormat.format(date)
    }


}
