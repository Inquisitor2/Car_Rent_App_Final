package com.example.carrent.models

data class User(
    val name: String = "",
    val lastname: String = "",
    val email: String = "",
    val phone: String = "",
    val username: String = "",
    val password: String = "",
    val reservations: List<Car> = emptyList(),
    val balance: Int = 0,
    val bankCards: List<bankCard> = emptyList(),
    val moneySpent: Double = 0.0,
)
