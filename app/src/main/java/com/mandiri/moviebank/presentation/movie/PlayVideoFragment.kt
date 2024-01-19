
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mandiri.moviebank.databinding.FragmentVideoScreenBinding
import com.mandiri.moviebank.presentation.movie.viewmodel.MovieDetailViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class PlayVideoFragment : Fragment() {
    private var _binding: FragmentVideoScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailViewModel by viewModels()

    private val movieId: Int by lazy {
        arguments?.getInt("MOVIE_ID", -1) ?: -1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVideoScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (movieId != -1) {
            playVideo()
            viewModel.setData(movieId)
        }

//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
//            requireActivity().supportFragmentManager.popBackStack()
//        }
    }

    private fun playVideo() {
        val youtubePlayerView = binding.youtubeVideo

        viewModel.video.observe(viewLifecycleOwner) { videos ->
            val teaserOrTrailer = videos.find { it.type == "Teaser" || it.type == "Trailer" }

            if (teaserOrTrailer != null) {
                youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(teaserOrTrailer.key, 0f)
                    }
                })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(movieId: Int): PlayVideoFragment {
            val fragment = PlayVideoFragment()
            val bundle = Bundle().apply {
                putInt("MOVIE_ID", movieId)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}
