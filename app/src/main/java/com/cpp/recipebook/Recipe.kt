package com.cpp.recipebook

data class Recipe (
    val name: String,
    val cuisine: String,
    val ingredients: MutableList<String>,
    val directions: MutableList<String>,
    val notes: MutableList<String>,
    val image: String
)