package com.prueba.pruebatecnica.ui
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prueba.pruebatecnica.ui.userdetail.UserDetailScreen
import com.prueba.pruebatecnica.ui.userlist.UserListScreen
import com.prueba.pruebatecnica.ui.userlist.UserListViewModel

@Composable
fun UserListApp() {
    val navController = rememberNavController()
    val viewModel: UserListViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "user_list") {
        composable("user_list") {
            UserListScreen(navController, viewModel)
        }
        composable("user_detail") {
            UserDetailScreen(viewModel)
        }
    }
}