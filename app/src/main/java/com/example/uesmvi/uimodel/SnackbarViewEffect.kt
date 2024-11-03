package com.example.uesmvi.uimodel

sealed class SnackbarViewEffect {
    data class ShowSnackbarView(val message: String, val actionLabel: String? = null) :
        SnackbarViewEffect()

}