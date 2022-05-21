package com.naldana.dummydictionary.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "antonym_table", primaryKeys = ["term", "antonym_term"])
data class Antonym(
    @ColumnInfo(name = "term") val term: String,
    @ColumnInfo(name = "antonym_term") val synonymTerm: String
)