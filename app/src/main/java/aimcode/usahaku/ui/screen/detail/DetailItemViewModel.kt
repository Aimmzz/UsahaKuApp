package aimcode.usahaku.ui.screen.detail

import aimcode.usahaku.data.ItemRepository
import aimcode.usahaku.model.product.Product1
import aimcode.usahaku.model.OrderItem
import aimcode.usahaku.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailItemViewModel(
    private val repository: ItemRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderItem>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderItem>>
        get() = _uiState

    fun getItemById(itemId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderItemById(itemId))
        }
    }

    fun addToCart(product1: Product1, count: Int) {
        viewModelScope.launch {
            repository.updateOrderItem(product1.id, count)
        }
    }
}