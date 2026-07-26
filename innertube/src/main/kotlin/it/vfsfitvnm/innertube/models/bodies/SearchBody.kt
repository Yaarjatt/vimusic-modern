package com.hmusic.new.innertube.models.bodies

import com.hmusic.new.innertube.models.Context
import kotlinx.serialization.Serializable

@Serializable
data class SearchBody(
    val context: Context = Context.DefaultAndroid,
    val query: String,
    val params: String
)
