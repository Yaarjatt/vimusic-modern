package com.hmusic.new.innertube.models.bodies

import com.hmusic.new.innertube.models.Context
import kotlinx.serialization.Serializable

@Serializable
data class QueueBody(
    val context: Context = Context.DefaultAndroid,
    val videoIds: List<String>? = null,
    val playlistId: String? = null,
)
