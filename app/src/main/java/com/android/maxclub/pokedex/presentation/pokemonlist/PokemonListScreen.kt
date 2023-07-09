package com.android.maxclub.pokedex.presentation.pokemonlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.maxclub.pokedex.R
import com.android.maxclub.pokedex.util.Screen
import com.android.maxclub.pokedex.presentation.pokemonlist.components.PokemonList
import com.android.maxclub.pokedex.presentation.pokemonlist.components.PokemonListTopSection
import com.android.maxclub.pokedex.presentation.pokemonlist.components.SearchBar

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel(),
) {
    val pokemonList = viewModel.pagingPokemonList.collectAsLazyPagingItems()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            PokemonListTopSection(modifier = Modifier.fillMaxWidth())

            SearchBar(
                hint = stringResource(R.string.search_hint),
                text = searchQuery,
                onSearch = viewModel::onSearch,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            PokemonList(
                pokemonList = pokemonList,
                onItemClick = { pokemonName, dominantColor ->
                    navController.navigate("${Screen.PokemonDetail.route}/$pokemonName/$dominantColor")
                }
            )
        }
    }
}