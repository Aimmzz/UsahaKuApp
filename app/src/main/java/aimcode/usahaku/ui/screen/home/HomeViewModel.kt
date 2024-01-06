package aimcode.usahaku.ui.screen.home

import aimcode.usahaku.data.ItemRepository
import aimcode.usahaku.model.OrderItem
import aimcode.usahaku.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ItemRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderItem>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderItem>>>
        get() = _uiState

    fun getAllItems() {
        viewModelScope.launch {
            repository.getAllItems()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}