# User Management with MVI Pattern in Jetpack Compose

This project is a **User Management App** built using Jetpack Compose, implementing the **MVI (Model-View-Intent)** architecture. The app enables users to add, search, and delete user information while demonstrating structured state management using a ViewModel and repository pattern.

## Features
- **Add New Users:** Input user name and email to add to the list.
- **Search Users:** Search users by name or email.
- **Undo Delete:** Option to undo the last deletion action.
- **Clear All Users:** Remove all users from the list.
- **Snackbar Notifications:** Display feedback messages for actions.

## Tech Stack
- **Jetpack Compose** for UI
- **MVI Architecture** for structured state management
- **Coroutines** for asynchronous operations
- **Kotlin** for language

## Project Structure

- `UserModel` - Data class for user information.
- `UserListViewModel` - Manages UI state and processes user intents.
- `UserRepository` - Interface defining user CRUD functions.
- `UserRepositoryImp` - Implementation of `UserRepository` with simulated asynchronous operations.

## Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/yourprojectname.git
    ```

2. Open the project in Android Studio.

3. Sync Gradle and build the project.

4. Run the app on an emulator or Android device.

## Code Highlights

