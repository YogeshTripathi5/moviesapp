package tmdb.arch.movieapp.ui.screens.discover

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import tmdb.arch.movieapp.R
import tmdb.arch.movieapp.databinding.MoviesDiscoverBinding
import tmdb.arch.movieapp.ui.screens.discover.adapters.MovieListAdapter
import tmdb.arch.movieapp.utils.delegates.autoNull
import tmdb.arch.movieapp.utils.delegates.viewBinding
import tmdb.arch.movieapp.utils.extensions.collectRepeatOnStart

class DiscoverMovies : Fragment(R.layout.movies_discover) {

    private val binding by viewBinding(MoviesDiscoverBinding::bind)
    private val viewModel by viewModel<DiscoverMoviesVM>()
    private val listAdapter by autoNull { MovieListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        subscribeUi()
    }

    private fun initView() {
        binding.listView.adapter = listAdapter
    }

    private fun subscribeUi() {
        viewModel.movies.collectRepeatOnStart(viewLifecycleOwner) {pagingData ->
            viewLifecycleOwner.lifecycleScope.launch {
                listAdapter.submitData(pagingData)
            }
        }
    }
}
