package kr.study.android.calculator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History (
    @PrimaryKey
    val uid: Int?,
    @ColumnInfo(name = "expression")
    val expression: String?,
    @ColumnInfo(name = "result")
    val result: String?
)