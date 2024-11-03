package com.example.uesmvi.repository

import com.example.uesmvi.model.UserModel
import kotlinx.coroutines.delay

/*
Your UserRepositoryImp class looks well-implemented.
You've successfully set up each function to interact with a users list,
using coroutines (suspend and delay) to simulate asynchronous operations,
such as network or database requests.
Here’s a quick review and a couple of minor suggestions:
 */
class UserRepositoryImp : UserRepository {

    private var users = mutableListOf<UserModel>()

    override suspend fun getUser(): List<UserModel> {
        delay(3000)
        return users
    }

    override suspend fun addUser(userModel: UserModel): List<UserModel> {
        delay(3000)
        users.add(userModel)
        return users
    }

    override suspend fun deleteUser(userModel: UserModel): List<UserModel> {
        delay(3000)
        users.remove(userModel)
        return users
    }

    override suspend fun clearUsers(): List<UserModel> {
        delay(3000)
        users.clear()
        return users
    }
}