package aimcode.usahaku.model.product

import aimcode.usahaku.model.stock.Stock
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

data class Product1(
    val id: Long,
    val image: Int,
    val title: String,
    val requiredPoint: Int,
)

@Entity(
    tableName = "product",
    foreignKeys = [ForeignKey(
        entity = Stock::class,
        parentColumns = ["idStock"],
        childColumns = ["idStock"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Product(
    @PrimaryKey(autoGenerate = true) val idProduct: Int = 0,
    val idStock: Int
)