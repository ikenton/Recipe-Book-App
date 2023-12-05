package com.cpp.recipebook

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val cuisine: String,
    val ingredients: String,
    val directions: String,
    val notes: String,
    val image: String
)