package com.example.kata

data class User(
    val id: Int? = null,
    val name: String? = null,
    val userName: String? = null,
    val email: String? = null,
    val address : Address? = null,
    val phone: String? = null,
    val website: String? = null
)

data class Address(
    val street: String? = null,
    val suite: String? = null,
    val city: String? = null,
    val zipCode: String? = null
)

data class Comment (
    val postId: Int = 0,
    val id: Int = 0,
    val name: String? = null,
    val email: String? = null,
    val body: String? = null
)

data class Post (
    val userId: Int = 0,
    val id: Int? = null,
    val title: String? = null,
    val body: String? = null
)