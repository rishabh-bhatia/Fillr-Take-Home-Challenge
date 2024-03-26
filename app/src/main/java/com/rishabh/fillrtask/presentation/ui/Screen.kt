package com.rishabh.fillrtask.presentation.ui

sealed class Screen(val route: String) {
    data object ImageListScreen: Screen("image_list_screen")
    data object ImageDetailScreen: Screen("image_detail_screen")
}