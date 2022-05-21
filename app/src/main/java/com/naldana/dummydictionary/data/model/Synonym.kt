package com.naldana.dummydictionary.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "synonym_table", primaryKeys = ["term", "synonym_term"])
data class Synonym(
    @ColumnInfo(name = "term") val term: String,
    @ColumnInfo(name = "synonym_term")val synonymTerm: String
)