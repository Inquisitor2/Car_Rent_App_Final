package com.example.carrent.models

data class bankCard(
    val bankName: String = "",
    val cardOwner: String = "",
    val cardNumber: String = "",
    val expirationDate: String = "",
    val cvv: String = "",
    val cardType: String = ""
)
