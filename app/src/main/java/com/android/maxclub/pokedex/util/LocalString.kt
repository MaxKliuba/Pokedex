package com.android.maxclub.pokedex.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

sealed class LocalString {
    data class Dynamic(val value: String) : LocalString()
    class Resource(
        @StringRes val resId: Int,
        vararg val args: Any,
    ) : LocalString()

    fun asString(context: Context): String =
        when (this) {
            is Dynamic -> value
            is Resource -> context.getString(resId, *args)
        }

    @Composable
    fun asString(): String = asString(LocalContext.current)
}