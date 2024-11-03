package com.example.uesmvi.repository

import com.example.uesmvi.model.UserModel

/*
Your UserRepository interface is a great start for
handling CRUD operations for UserModel objects.
However, there are a couple of minor adjustments
to make it more logical and consistent
 */

//
interface UserRepository {
    suspend fun getUser(): List<UserModel>
    suspend fun addUser(userModel: UserModel): List<UserModel>
    suspend fun deleteUser(userModel: UserModel): List<UserModel>
    suspend fun clearUsers(): List<UserModel>
}