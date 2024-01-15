package aimcode.usahaku.ui.screen.product

import aimcode.usahaku.di.ItemInjection
import aimcode.usahaku.model.OrderItem
import aimcode.usahaku.ui.ViewModelFactory
import aimcode.usahaku.ui.common.UiState
import aimcode.usahaku.ui.component.ItemCard
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = viewModel(
        factory = ViewModelFactory(ItemInjection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
//    Box(
//        modifier = modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center,
//    ) {
//        Text(stringResource(R.string.menu_home))
//    }
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllItems()
            }
            is UiState.Success -> {
                ProductContent(
                    orderReward = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun ProductContent(
    orderReward: List<OrderItem>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(orderReward) { data ->
            ItemCard(
                image = data.product1.image,
                title = data.product1.title,
                requiredPoint = data.product1.requiredPoint,
                modifier = Modifier.clickable {
                    navigateToDetail(data.product1.id)
                }
            )
        }
    }
}