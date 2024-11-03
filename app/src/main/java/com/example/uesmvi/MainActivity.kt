package com.example.uesmvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels

import com.example.uesmvi.view.components.UserListScreen
import com.example.uesmvi.viewmodel.UserListViewModel

class MainActivity : ComponentActivity() {

    private val userListViewModel: UserListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserListScreen(userListViewModel = userListViewModel)
        }
    }
}
