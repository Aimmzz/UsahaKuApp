package aimcode.usahaku.model

import aimcode.usahaku.model.item.Item
import aimcode.usahaku.model.item.ItemDao
import aimcode.usahaku.model.product.Product
import aimcode.usahaku.model.product.ProductDao
import aimcode.usahaku.model.stock.Stock
import aimcode.usahaku.model.stock.StockDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Item::class, Stock::class, Product::class],
    version = 1,
    exportSchema = false
)
abstract class UsahaKuDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun stockDao(): StockDao
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var Instance: UsahaKuDatabase? = null

        fun getDatabase(context: Context): UsahaKuDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, UsahaKuDatabase::class.java, "usahaku_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}