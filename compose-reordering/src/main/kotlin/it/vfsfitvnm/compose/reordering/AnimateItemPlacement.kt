package com.hmusic.new.compose.reordering

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.ui.Modifier

@ExperimentalFoundationApi
context(LazyItemScope)
@ExperimentalFoundationApi
fun Modifier.animateItemPlacement(reorderingState: ReorderingState) =
    if (reorderingState.draggingIndex == -1) animateItemPlacement() else this
