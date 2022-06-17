package fastcampus.aop.part3.chapter04.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    @SerializedName("itemId") val id: Long,
    val title: String,
    val description: String,
    val coverSmallUrl: String
) : Parcelable
