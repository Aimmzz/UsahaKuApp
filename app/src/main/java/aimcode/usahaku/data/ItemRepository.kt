package aimcode.usahaku.data

import aimcode.usahaku.model.ItemDataSource
import aimcode.usahaku.model.OrderItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ItemRepository {

    private val orderItems = mutableListOf<OrderItem>()

    init {
        if (orderItems.isEmpty()) {
            ItemDataSource.dummyItem.forEach {
                orderItems.add(OrderItem(it, 0))
            }
        }
    }

    fun getAllItems(): Flow<List<OrderItem>> {
        return flowOf(orderItems)
    }

    fun getOrderItemById(itemId: Long): OrderItem {
        return orderItems.first {
            it.item.id == itemId
        }
    }

    fun updateOrderItem(itemId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderItems.indexOfFirst { it.item.id == itemId }
        val result = if (index >= 0) {
            val orderItem = orderItems[index]
            orderItems[index] =
                orderItem.copy(item = orderItem.item, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderItems(): Flow<List<OrderItem>> {
        return getAllItems()
            .map { orderItems ->
                orderItems.filter { orderItem ->
                    orderItem.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: ItemRepository? = null

        fun getInstance(): ItemRepository =
            instance ?: synchronized(this) {
                ItemRepository().apply {
                    instance = this
                }
            }
    }
}