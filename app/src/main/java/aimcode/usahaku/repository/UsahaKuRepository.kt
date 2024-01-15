package aimcode.usahaku.repository

import aimcode.usahaku.model.item.Item
import aimcode.usahaku.model.item.ItemDao
import aimcode.usahaku.model.product.Product
import aimcode.usahaku.model.product.ProductDao
import aimcode.usahaku.model.stock.Stock
import aimcode.usahaku.model.stock.StockDao
import androidx.lifecycle.LiveData

class UsahaKuRepository(
    private val itemDao: ItemDao,
    private val stockDao: StockDao,
    private val productDao: ProductDao
) {
//    fun getAllItems(): LiveData<List<Item>> {
//        return itemDao.getAllItem().asL
//    }
    fun getAllStock(): List<Stock> {
        return stockDao.getAllStock()
    }
    fun getAllProduct(): List<Product> {
        return productDao.getAllProduct()
    }
}