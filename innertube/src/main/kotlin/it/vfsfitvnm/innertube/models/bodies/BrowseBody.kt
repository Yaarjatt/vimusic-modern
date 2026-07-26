package com.hmusic.new.innertube.models.bodies

import com.hmusic.new.innertube.models.Context
import kotlinx.serialization.Serializable

@Serializable
data class BrowseBody(
    val context: Context = Context.DefaultAndroid,
    val browseId: String,
    val params: String? = null
)
