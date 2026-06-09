package com.haridev.makkalmart.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.haridev.makkalmart.ui.screens.DisplayProducts
import com.haridev.makkalmart.ui.screens.ProductDetailsScreen
import com.haridev.makkalmart.ui.screens.ProductFormScreen
import com.haridev.makkalmart.viewmodel.MakkalMartViewModel

@Composable
fun AppNavGraph(){
    val navController = rememberNavController()

    val viewModel: MakkalMartViewModel = viewModel()
    NavHost(navController=navController, startDestination = "display_products"){
        composable("display_products"){
            DisplayProducts(navController=navController,viewModel=viewModel)
        }
        composable("product_details/{id}"){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            ProductDetailsScreen(productId = id,navController = navController,viewModel = viewModel)
        }
        composable("add_product") {
            ProductFormScreen(navController = navController, viewModel = viewModel)
        }
        composable("edit_product/{id}"){backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            ProductFormScreen(productId = id,navController = navController, viewModel = viewModel)
        }
    }
}