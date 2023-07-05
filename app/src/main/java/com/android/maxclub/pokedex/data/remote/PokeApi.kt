package com.android.maxclub.pokedex.data.remote

import com.android.maxclub.pokedex.data.remote.dto.PokemonDto
import com.android.maxclub.pokedex.data.remote.dto.PokemonListDto
import com.android.maxclub.pokedex.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListDto

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ): PokemonDto

    companion object {
        const val STARTING_PAGE_INDEX = 0

        fun create(): PokeApi =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokeApi::class.java)
    }
}