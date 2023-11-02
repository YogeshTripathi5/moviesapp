package tmdb.arch.movieapp.ui.screens.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DiscoverMoviesVM :ViewModel() {

    private val _text = MutableStateFlow("Hey")
    val text:StateFlow<String> get() = _text.asStateFlow()

    init {
        viewModelScope.launch {

            _text.tryEmit("hello World")
            delay(2000)
            _text.tryEmit("hello00000 World")

        }

    }
}