package aimcode.usahaku.ui.component

import aimcode.usahaku.R
import aimcode.usahaku.ui.theme.UsahaKuTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CartItem(
    itemId: Long,
    image: Int,
    title: String,
    totalPoint: Int,
    count: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(
                    R.string.required_point,
                    totalPoint
                ),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
        }
        ProductCounter(
            orderId = itemId,
            orderCount = count,
            onProductIncreased = { onProductCountChanged(itemId, count + 1) },
            onProductDecreased = { onProductCountChanged(itemId, count - 1) },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
    UsahaKuTheme {
//        CartItem(
//            4, R.drawable.reward_5, "Jaket Hoodie Dicoding", 4000, 0,
//            onProductCountChanged = { itemId, count -> },
//        )
    }
}