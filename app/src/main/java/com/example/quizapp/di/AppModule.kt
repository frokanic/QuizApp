package com.example.quizapp.di

import com.example.quizapp.data.repository.QuizRepositoryImpl
import com.example.quizapp.domain.interactor.QuizInteractor
import com.example.quizapp.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuizRepository(): QuizRepository {
        return QuizRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideQuizInteractor(repository: QuizRepository): QuizInteractor {
        return QuizInteractor(repository)
    }

}