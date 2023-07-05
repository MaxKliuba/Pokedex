package com.android.maxclub.pokedex.presentation

sealed class Screen(val route: String) {
    object PokemonListScreen : Screen("pokemon_list_screen")

    object PokemonDetail : Screen("pokemon_detail_screen") {
        const val ARG_POKEMON_NAME = "pokemonName"
        const val ARG_DOMINANT_COLOR = "dominantColor"

        override val fullRoute: String
            get() = "${PokemonDetail.route}/{$ARG_POKEMON_NAME}/{$ARG_DOMINANT_COLOR}"
    }

    open val fullRoute: String
        get() = route
}
