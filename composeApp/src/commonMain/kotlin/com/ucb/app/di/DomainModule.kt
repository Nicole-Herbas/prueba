package com.ucb.app.di

import com.ucb.app.github.domain.usecase.GetAvatarUseCase
import com.ucb.app.movie.domain.usecase.GetMovieRatingUseCase
import com.ucb.app.movie.domain.usecase.GetMoviesUseCase
import com.ucb.app.movie.domain.usecase.SetMovieRatingUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetAvatarUseCase)
    singleOf(::GetMoviesUseCase)
    singleOf(::GetMovieRatingUseCase)
    singleOf(::SetMovieRatingUseCase)
}