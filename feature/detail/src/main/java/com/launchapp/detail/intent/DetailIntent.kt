package com.launchapp.detail.intent

sealed class DetailIntent {
    data class LoadDetails(val id: String) : DetailIntent()
}