package aimcode.usahaku.model.product

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAllProduct(): List<Product>
}