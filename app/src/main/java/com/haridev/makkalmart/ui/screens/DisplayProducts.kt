package com.haridev.makkalmart.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow
import com.haridev.makkalmart.viewmodel.MakkalMartViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayProducts(navController: NavHostController, viewModel: MakkalMartViewModel) {

    LaunchedEffect (Unit) {
        viewModel.loadProduct()
    }

    val products = viewModel.products.collectAsState()
    var searchText by remember { mutableStateOf("") }
    val filteredProducts = products.value.filter {
        it.title.contains(searchText, ignoreCase = true) || it.category.contains(searchText, ignoreCase = true)
    }
    Scaffold(
        topBar = { TopAppBar(title = {
                Row() {
                    Icon(imageVector = Icons.Default.ShoppingCart,contentDescription = "Cart",
                        tint = MaterialTheme.colorScheme.onPrimary)
                    Text(text = "Makkal Mart", fontWeight = FontWeight.SemiBold)
                }
             },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("add_product")
            }, containerColor = MaterialTheme.colorScheme.primary) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->

        if (products.value.isEmpty()) {
            Column (modifier = Modifier.padding(innerPadding).fillMaxWidth(),
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
            }
        } else {

            Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(12.dp)) {
                OutlinedTextField(value = searchText, onValueChange = { searchText = it },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp), singleLine = true,
                    placeholder = {
                        Text("Search products")
                    }
                )
                LazyVerticalGrid  (columns = GridCells.Fixed(2),
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)) {

                    items(filteredProducts) { product ->

                        Card(modifier = Modifier.fillMaxWidth()
                            .height(320.dp).clickable{
                                navController.navigate("product_details/${product.id}")
                            }, elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        ) {
                            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                AsyncImage(model = "${product.image}",contentDescription = product.title, modifier = Modifier.fillMaxWidth().height(140.dp))
                                Text(text = product.title, style = MaterialTheme.typography.titleMedium,
                                    maxLines = 2, fontWeight = FontWeight.Bold,overflow = TextOverflow.Ellipsis)
                                Text(text = product.category, color = MaterialTheme.colorScheme.primary)
                                Text(text = "₹ ${product.price}")
                                Text(text = "⭐ ${product.ratingRate} (${product.ratingCount} reviews)")
                            }
                        }
                    }
                }
            }
        }
    }
}