package com.cpp.recipebook

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Recipe (
    @PrimaryKey val id: UUID,
    val name: String,
    val cuisine: String,
    val ingredients: String,
    val directions: String,
    val notes: String,
    val image: String
)