package aimcode.usahaku.ui.screen.cart

import aimcode.usahaku.model.OrderItem

data class CartState(
    val orderReward: List<OrderItem>,
    val totalRequiredPoint: Int
)