package fastcampus.aop.part3.chapter04.model

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("itemId") val id: Long,
    val title: String,
    val description: String,
    val coverSmallUrl: String
)
