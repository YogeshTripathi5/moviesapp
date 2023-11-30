package tmdb.arch.movieapp.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import tmdb.arch.movieapp.domain.remote.MovieService

class MoviesRepo(private val remoteService: MovieService) {

    fun getLatestMovies() = Pager(config = PagingConfig(pageSize = 50)) {
        LatestMoviesPagingSource(remoteService)
    }.flow
}
