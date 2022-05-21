package com.naldana.dummydictionary.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey @ColumnInfo(name = "term") @SerializedName("term") val term: String,
    @ColumnInfo(name = "definition") @SerializedName("description") val definition: String,
    @ColumnInfo(name = "is_fav", defaultValue = "0")  var isFav: Boolean = false
)