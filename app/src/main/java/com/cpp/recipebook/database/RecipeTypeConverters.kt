package com.cpp.recipebook.database

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class RecipeTypeConverters {
    private val moshi = Moshi.Builder().build()
    private val listType = com.squareup.moshi.Types.newParameterizedType(
        MutableList::class.java,
        String::class.java
    )
    private val jsonAdapter: JsonAdapter<MutableList<String>> = moshi.adapter(listType)

    @TypeConverter
    fun fromString(value: String): MutableList<String>? {
        return jsonAdapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(list: MutableList<String>): String {
        return jsonAdapter.toJson(list)
    }
}