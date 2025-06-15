package com.example.carrent.models

data class Car(
    val brand: String = "",
    val model: String = "",
    val year: Int = 0,
    val price: String = "",
    val imageUrl: String = "",
    val rating: Float = 0f,
    val isHotDeal: Boolean = false,
    val isNewModel: Boolean = false,
    val mileage: Int = 0,
    val horsepower: Int = 0,
    val location: String = "",
    val ownerName: String = "",
    val ownerPhone: String = "",
    val ownerEmail: String = "",
    val fuelType: String = "",
    val topSpeed: Int = 0,
    val bodyType: String = "",
    val doors: Int = 4,
    val seats: Int = 5
)
