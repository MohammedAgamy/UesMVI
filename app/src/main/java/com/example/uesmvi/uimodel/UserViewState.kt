package com.example.uesmvi.uimodel

import com.example.uesmvi.model.UserModel
/*
Your UserViewState data class is well-structured for managing the UI state in an MVI architecture.
Here’s a breakdown of its fields and their intended usage:
 */
data class UserViewState(
    val isLoading: Boolean = false,
    val users: List<UserModel> = emptyList(),
    val name: String = "",
    val email: String = "",
    val nameError: Boolean = false,
    val emailError: Boolean = false,
    val searchQuery: String = "",
    val recentlyDeletedUser: UserModel? = null
)