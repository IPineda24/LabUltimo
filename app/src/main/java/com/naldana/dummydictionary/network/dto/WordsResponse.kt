package com.naldana.dummydictionary.network.dto

import com.google.gson.annotations.SerializedName
import com.naldana.dummydictionary.data.model.Word

data class WordsResponse (
@SerializedName("count")
val count:Int,
@SerializedName("words")
val words:List<Word>
)