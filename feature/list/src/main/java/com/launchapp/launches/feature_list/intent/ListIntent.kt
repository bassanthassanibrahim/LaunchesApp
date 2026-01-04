package com.launchapp.launches.feature_list.intent

sealed class ListIntent {
    object LoadInitial : ListIntent()
    object LoadMore : ListIntent()
    object Refresh : ListIntent()
}