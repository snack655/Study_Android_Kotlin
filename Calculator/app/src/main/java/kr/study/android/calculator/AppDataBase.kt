package kr.study.android.calculator

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.study.android.calculator.dao.HistoryDao
import kr.study.android.calculator.model.History

@Database(
    entities = [History::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}