package com.example.uesmvi.model

//A data class in Kotlin is a class that is intended to hold data
//equals(): Compares two instances of the class based on their properties.
//hashCode(): Generates a hash code for an instance of the class based on its properties.
//toString(): Returns a string representation of an instance of the class.
//copy(): Creates a new instance of the class with the same values for its properties.
// data class best practice to building model
data class UserModel(val id: Int, val name: String, val email: String) {
}