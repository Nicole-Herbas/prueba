package com.ucb.app.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.app.movie.domain.usecase.GetMovieRatingUseCase
import com.ucb.app.movie.domain.usecase.GetMoviesUseCase
import com.ucb.app.movie.domain.usecase.SetMovieRatingUseCase
import com.ucb.app.movie.presentation.state.MovieEfffect
import com.ucb.app.movie.presentation.state.MovieEvent
import com.ucb.app.movie.presentation.state.MovieUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieViewModel(
    val moviesUseCase: GetMoviesUseCase,
    val getMovieRatingUseCase: GetMovieRatingUseCase,
    val setMovieRatingUseCase: SetMovieRatingUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieUiState())
    val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<MovieEfffect>()
    val effects = _effects.asSharedFlow()

    init {
        load()
    }

    fun onEvent(event: MovieEvent) {
        when (event) {
            is MovieEvent.OnSelectMovie -> {
                viewModelScope.launch {
                    _effects.emit(MovieEfffect.NavigateToDetail(event.movie))
                }
            }
            is MovieEvent.OnRateMovie -> {
                setMovieRatingUseCase.invoke(event.movieId, event.rating)
                _state.update {
                    it.copy(ratings = it.ratings + (event.movieId to event.rating))
                }
            }
        }
    }

    fun load() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val list = moviesUseCase.invoke()
            val ratings = getMovieRatingUseCase.getAllRatings()
            _state.update { it.copy(list = list, ratings = ratings, isLoading = false) }
        }
    }

    fun refreshRatings() {
        _state.update { it.copy(ratings = getMovieRatingUseCase.getAllRatings()) }
    }
}