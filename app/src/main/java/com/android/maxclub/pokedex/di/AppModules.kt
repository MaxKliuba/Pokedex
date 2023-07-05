package com.android.maxclub.pokedex.di

import com.android.maxclub.pokedex.data.remote.PokeApi
import com.android.maxclub.pokedex.data.repository.PokemonRepositoryImpl
import com.android.maxclub.pokedex.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun providePokeApi(): PokeApi = PokeApi.create()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindPokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository
}