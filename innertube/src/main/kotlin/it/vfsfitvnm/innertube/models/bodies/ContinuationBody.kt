package com.hmusic.new.innertube.models.bodies

import com.hmusic.new.innertube.models.Context
import kotlinx.serialization.Serializable

@Serializable
data class ContinuationBody(
    val context: Context = Context.DefaultAndroid,
    val continuation: String,
)
