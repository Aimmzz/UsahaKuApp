package aimcode.usahaku.ui.navigation

sealed class Screen(val route: String) {
    object Product : Screen("product")
    object Cart : Screen("cart")
    object Home : Screen("home")
    object AddItem: Screen("AddItem")
    object DetailItem : Screen("product/{itemId}") {
        fun createRoute(itemId: Long) = "product/$itemId"
    }
}