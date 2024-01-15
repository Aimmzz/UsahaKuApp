package aimcode.usahaku.model.stock

import androidx.room.Dao
import androidx.room.Query

@Dao
interface StockDao {
    @Query("SELECT * FROM stocks")
    fun getAllStock(): List<Stock>
}