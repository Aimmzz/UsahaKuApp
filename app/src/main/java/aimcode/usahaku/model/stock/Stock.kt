package aimcode.usahaku.model.stock

import aimcode.usahaku.model.item.Item
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "stocks",
    foreignKeys = [ForeignKey(
        entity = Item::class,
        parentColumns = ["idItem"],
        childColumns = ["idItem"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Stock (

    @PrimaryKey(autoGenerate = true)
    val idStock: Int = 0,

    val idItem: Int,
    val stock: Int,
    val price: Int,
    val createDate: Date,
    val expiresDate: Date,
)