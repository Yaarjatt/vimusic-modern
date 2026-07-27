package com.hmusic.new.compose.reordering

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.ui.Modifier

@ExperimentalFoundationApi
fun Modifier.animateItemPlacement(reorderingState: ReorderingState, scope: LazyItemScope) =
    if (reorderingState.draggingIndex == -1) animateItemPlacement() else this
