package com.example.uesmvi.view.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.uesmvi.viewmodel.UserListViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.uesmvi.uimodel.SnackbarViewEffect
import com.example.uesmvi.uimodel.UserViewIntent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(userListViewModel: UserListViewModel) {
    val viewState by userListViewModel.viewState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    // Effect collector for snackbar
    LaunchedEffect(userListViewModel) {
        userListViewModel.effectFlow.collect { effect ->
            when (effect) {
                is SnackbarViewEffect.ShowSnackbarView -> {
                    val result = snackbarHostState.showSnackbar(
                        message = effect.message,
                        withDismissAction = true,
                        actionLabel = effect.actionLabel
                    )
                    if (result == SnackbarResult.ActionPerformed && effect.actionLabel == "Undo") {
                        userListViewModel.handleIntent(UserViewIntent.UndoDelete)
                        snackbarHostState.currentSnackbarData?.dismiss()
                    }
                }
            }
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("User Management", fontSize = 22.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6200EE))
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                SearchInput(
                    query = viewState.searchQuery,
                    onQueryChange = {
                        userListViewModel.handleIntent(
                            UserViewIntent.SearchUserView(
                                it
                            )
                        )
                    },
                    onSearchText = { userListViewModel.handleIntent(UserViewIntent.LoadUsers) } // Reload all users when cleared
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    when {
                        viewState.isLoading -> CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                        viewState.users.isEmpty() && !viewState.isLoading -> Text("No users available")
                        else -> {
                            LazyColumn(
                                state = listState,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                items(viewState.users) { user ->
                                    UserItem(user = user, onDeleteUser = {
                                        userListViewModel.handleIntent(
                                            UserViewIntent.DeleteUserView(
                                                it
                                            )
                                        )
                                    })
                                }
                            }
                        }
                    }
                }

                // Input section at the bottom
                UserInPut(
                    name = viewState.name,
                    email = viewState.email,
                    nameError = viewState.nameError,
                    emailError = viewState.emailError,
                    onNameChange = { userListViewModel.handleIntent(UserViewIntent.UpdateName(it)) },
                    onEmailChange = { userListViewModel.handleIntent(UserViewIntent.UpdateEmail(it)) },
                    onAddUser = {
                        userListViewModel.handleIntent(
                            UserViewIntent.AddUserView(
                                it.first,
                                it.second
                            )
                        )
                        scope.launch {
                            listState.animateScrollToItem(viewState.users.size) // Scroll to the last added user
                        }
                    },
                    onClearUsers = { userListViewModel.handleIntent(UserViewIntent.ClearUsers) }
                )
            }
        }
    )
}

class MockUserListViewModel : UserListViewModel()


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val viewModel = MockUserListViewModel() // Use mock data instead of the real ViewModel
    UserListScreen(userListViewModel = viewModel)
}


