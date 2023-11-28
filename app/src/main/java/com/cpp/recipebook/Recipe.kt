package com.cpp.recipebook

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val cuisine: String,
    val ingredients: MutableList<String>,
    val directions: MutableList<String>,
    val notes: MutableList<String>,
    val image: String? = null
)