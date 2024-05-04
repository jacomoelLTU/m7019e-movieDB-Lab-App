package com.example.themoviedbv24.ui.uiHelper

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun getReviewCardScreenWidth() : Dp {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp.dp - 16.dp
}

@Composable
fun getScreenWidth() : Dp {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp.dp
}