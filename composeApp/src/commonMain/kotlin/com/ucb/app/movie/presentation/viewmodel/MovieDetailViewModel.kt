package com.ucb.app.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ucb.app.movie.domain.usecase.GetMovieRatingUseCase
import com.ucb.app.movie.domain.usecase.SetMovieRatingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieDetailViewModel(
    private val getMovieRatingUseCase: GetMovieRatingUseCase,
    private val setMovieRatingUseCase: SetMovieRatingUseCase
) : ViewModel() {

    private val _rating = MutableStateFlow(0)
    val rating = _rating.asStateFlow()

    fun loadRating(movieId: Int) {
        _rating.update { getMovieRatingUseCase.invoke(movieId) }
    }

    fun setRating(movieId: Int, stars: Int) {
        setMovieRatingUseCase.invoke(movieId, stars)
        _rating.update { stars }
    }
}
