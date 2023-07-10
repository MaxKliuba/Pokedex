package com.android.maxclub.pokedex.util

import androidx.compose.ui.graphics.Color
import com.android.maxclub.pokedex.domain.model.PokemonStat
import com.android.maxclub.pokedex.domain.model.PokemonType
import com.android.maxclub.pokedex.ui.theme.AtkColor
import com.android.maxclub.pokedex.ui.theme.DefColor
import com.android.maxclub.pokedex.ui.theme.HPColor
import com.android.maxclub.pokedex.ui.theme.SpAtkColor
import com.android.maxclub.pokedex.ui.theme.SpDefColor
import com.android.maxclub.pokedex.ui.theme.SpdColor
import com.android.maxclub.pokedex.ui.theme.TypeBug
import com.android.maxclub.pokedex.ui.theme.TypeDark
import com.android.maxclub.pokedex.ui.theme.TypeDragon
import com.android.maxclub.pokedex.ui.theme.TypeElectric
import com.android.maxclub.pokedex.ui.theme.TypeFairy
import com.android.maxclub.pokedex.ui.theme.TypeFighting
import com.android.maxclub.pokedex.ui.theme.TypeFire
import com.android.maxclub.pokedex.ui.theme.TypeFlying
import com.android.maxclub.pokedex.ui.theme.TypeGhost
import com.android.maxclub.pokedex.ui.theme.TypeGrass
import com.android.maxclub.pokedex.ui.theme.TypeGround
import com.android.maxclub.pokedex.ui.theme.TypeIce
import com.android.maxclub.pokedex.ui.theme.TypeNormal
import com.android.maxclub.pokedex.ui.theme.TypePoison
import com.android.maxclub.pokedex.ui.theme.TypePsychic
import com.android.maxclub.pokedex.ui.theme.TypeRock
import com.android.maxclub.pokedex.ui.theme.TypeSteel
import com.android.maxclub.pokedex.ui.theme.TypeWater
import java.util.Locale

fun PokemonType.toColor(): Color =
    when (name.lowercase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Gray
    }

fun PokemonStat.toColor(): Color =
    when (name.lowercase(Locale.ROOT)) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.Gray
    }

fun String.toPokemonStatAbbr(): String =
    when (lowercase(Locale.ROOT)) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> "(?)"
    }