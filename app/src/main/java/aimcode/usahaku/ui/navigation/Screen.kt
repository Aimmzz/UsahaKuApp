package aimcode.usahaku.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object History : Screen("history")
    object DetailItem : Screen("home/{itemId}") {
        fun createRoute(itemId: Long) = "home/$itemId"
    }
}