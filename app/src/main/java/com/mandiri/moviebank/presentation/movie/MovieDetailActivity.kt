package com.mandiri.moviebank.presentation.movie

import PlayVideoFragment
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.mandiri.moviebank.R
import com.mandiri.moviebank.adapter.ActorAdapter
import com.mandiri.moviebank.adapter.ImageAdapter
import com.mandiri.moviebank.data.remote.network.response.Backdrop
import com.mandiri.moviebank.data.remote.network.response.Cast
import com.mandiri.moviebank.databinding.ActivityDetailMovieBinding
import com.mandiri.moviebank.model.MovieDetailModel
import com.mandiri.moviebank.module.BookmarkViewModelFactory
import com.mandiri.moviebank.presentation.bookmark.viewmodel.BookmarkViewModel
import com.mandiri.moviebank.presentation.movie.viewmodel.MovieDetailViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.ln
import kotlin.math.pow

class MovieDetailActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var actorAdapter: ActorAdapter
    private lateinit var data: MovieDetailModel

    private val viewModel: MovieDetailViewModel by viewModels()
    private val viewModelBookmark: BookmarkViewModel by viewModels {
        BookmarkViewModelFactory(application)
    }

    private var movieId: Int? = 0

    companion object {
        lateinit var instance: MovieDetailActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieId = intent.getIntExtra("MOVIE_ID", -1)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnPlay.setOnClickListener {
            playVideo()
        }

        binding.ivPlay.setOnClickListener {
            playVideo()
        }

        var isSaved = false

        Log.d("tiara", "movieId $movieId")

        viewModelBookmark.isMovieSaved(movieId ?: 0)


        viewModelBookmark.isMovieSaved.observe(this@MovieDetailActivity) {
            isSaved = it
            Log.d("tiara", "isSaved $isSaved")
            if (isSaved) {
                binding.ivBookmark.setImageResource(R.drawable.bookmark_fill_svgrepo_com)
            } else {
                binding.ivBookmark.setImageResource(R.drawable.bookmark_svgrepo_com)
            }
        }



        binding.ivBookmark.setOnClickListener {
            binding.ivBookmark.setImageResource(R.drawable.bookmark_fill_svgrepo_com)

            lifecycleScope.launch {
                if (isSaved) {
                    viewModelBookmark.unsaveMovie(movieId ?: 0, data)
                } else {
                    viewModelBookmark.saveMovie(data)
                }

            }
        }

        setupView()
    }

    private fun setupView() {
        binding.progresBar.visibility = View.VISIBLE

        viewModel.progress.observe(this, { isLoading ->
            if (isLoading) {
                binding.progresBar.visibility = View.VISIBLE
                binding.appbar.visibility = View.GONE
                binding.nestedScrollView.visibility = View.GONE
                binding.btnPlay.visibility = View.GONE
            } else {
                binding.progresBar.visibility = View.GONE
                binding.appbar.visibility = View.VISIBLE
                binding.nestedScrollView.visibility = View.VISIBLE
                binding.btnPlay.visibility = View.VISIBLE
            }
        })

        viewModel.setData(movieId ?: 0)
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
        binding.tvTitle.text = data.original_title
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
            companies.append("${prCompany.name}" + if (index != data.production_companies.size - 1) ", " else " ")
        }
        binding.tvCompanies.text = "Production companies: $companies"
        binding.tvDescription.text = data.overview
        binding.tvReleasedDate.text = "Released: ${dateFormatter(data.release_date)}"
        binding.tvRuntime.text = "Runtime: ${data.runtime / 60} h ${data.runtime % 60} min"
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/${data.poster_path}")
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(binding.ivImage)
    }


    private fun numberFormatter(count: Long): String {
        if (count < 1000) return "" + count
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format(
            "%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1]
        )
    }

    private fun dateFormatter(data: String): String {
        val incomingDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val showedDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val date = incomingDateFormat.parse(data)
        return showedDateFormat.format(date)
    }

    fun playVideo() {
        val playVideoFragment = PlayVideoFragment.newInstance(movieId ?: 0)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        transaction.replace(android.R.id.content, playVideoFragment)
        transaction.commit()
    }


}
