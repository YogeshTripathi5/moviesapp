package tmdb.arch.movieapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tmdb.arch.movieapp.domain.repository.MoviesRepo
import tmdb.arch.movieapp.ui.screens.details.MoviesDetailsVM
import tmdb.arch.movieapp.ui.screens.discover.DiscoverMoviesVM
import tmdb.arch.movieapp.ui.screens.saved.SavedMoviesVM
import tmdb.arch.movieapp.ui.screens.search.SearchMoviesVM

val viewModels = module {
    viewModel { DiscoverMoviesVM(repo = get<MoviesRepo>()) }
    viewModel { MoviesDetailsVM() }
    viewModel { SavedMoviesVM() }
    viewModel { SearchMoviesVM() }
}
