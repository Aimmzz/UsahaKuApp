package aimcode.usahaku.model.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item (

    @PrimaryKey(autoGenerate = true)
    val idItem: Int = 0,

    val name: String,
    val type: String
)