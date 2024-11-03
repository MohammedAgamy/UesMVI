package com.example.uesmvi.uimodel

import com.example.uesmvi.model.UserModel
//يعبر عن الحدث او الاكشن من خلال المستخدم
/*
   In the Model-View-Intent (MVI) architectural pattern,
   an Intent represents a user action or event that expresses the user’s intention to interact with the application.
   In MVI, Intents are part of the flow that defines how the app handles user
   actions and updates the UI in a predictable andreactive way.

Key Concepts of Intent in MVI
User Interaction Representation:

Intents in MVI capture and represent what the user intends to do (e.g., loading data, searching, adding, deleting).
Each Intent corresponds to a single user action, making it straightforward to track the user’s
 current intent and ensure the application responds accordingly.
 */

sealed class UserViewIntent {

    data object LoadUsers : UserViewIntent()
    data class AddUserView(val name: String, val email: String) : UserViewIntent()
    data class DeleteUserView(val user: UserModel) : UserViewIntent()
    data object ClearUsers : UserViewIntent()
    data class SearchUserView(val query: String) : UserViewIntent()
    data class UpdateName(val name: String) : UserViewIntent()
    data class UpdateEmail(val email: String) : UserViewIntent()
    data object UndoDelete : UserViewIntent()
}