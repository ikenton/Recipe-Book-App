package com.cpp.recipebook

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe (
    @PrimaryKey val id: Int? = null,
    val name: String,
    val cuisine: String,
    val ingredients: String,
    val directions: String,
    val notes: String,
    val image: String
)