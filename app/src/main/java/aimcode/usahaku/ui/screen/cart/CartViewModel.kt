package aimcode.usahaku.ui.screen.cart

import aimcode.usahaku.data.ItemRepository
import aimcode.usahaku.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: ItemRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderItems() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderItems()
                .collect { orderItem ->
                    val totalRequiredPoint =
                        orderItem.sumOf { it.product1.requiredPoint * it.count }
                    _uiState.value = UiState.Success(CartState(orderItem, totalRequiredPoint))
                }
        }
    }

    fun updateOrderItem(itemId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderItem(itemId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderItems()
                    }
                }
        }
    }
}