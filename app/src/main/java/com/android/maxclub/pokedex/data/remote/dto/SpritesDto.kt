package com.android.maxclub.pokedex.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SpritesDto(
    @SerializedName("front_default") val frontDefault: String?,
    val other: Other,
) {
    data class Other(
        @SerializedName("dream_world") val dreamWorld: DreamWorld,
        val home: Home,
        @SerializedName("official-artwork") val officialArtwork: OfficialArtwork,
    ) {
        data class DreamWorld(
            @SerializedName("front_default") val frontDefault: String?
        )

        data class Home(
            @SerializedName("front_default") val frontDefault: String?
        )

        data class OfficialArtwork(
            @SerializedName("front_default") val frontDefault: String?
        )
    }
}