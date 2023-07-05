package com.android.maxclub.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.maxclub.pokedex.presentation.pokemonlist.PokemonListScreen
import com.android.maxclub.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.PokemonListScreen.route
                ) {
                    composable(route = Screen.PokemonListScreen.route) {
                        PokemonListScreen(
                            navController = navController
                        )
                    }
                    composable(
                        route = Screen.PokemonDetail.fullRoute,
                        arguments = listOf(
                            navArgument(name = Screen.PokemonDetail.ARG_POKEMON_NAME) {
                                type = NavType.StringType
                            },
                            navArgument(name = Screen.PokemonDetail.ARG_DOMINANT_COLOR) {
                                type = NavType.IntType
                            }
                        )
                    ) {
                        val pokemonName = remember {
                            it.arguments?.getString(Screen.PokemonDetail.ARG_POKEMON_NAME) ?: ""
                        }
                        val dominantColor = remember {
                            it.arguments?.getInt(Screen.PokemonDetail.ARG_DOMINANT_COLOR)
                                ?.let { Color(it) } ?: Color.White
                        }

                        // TODO PokemonDetailScreen()
                    }
                }
            }
        }
    }
}