package com.haridev.makkalmart.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.haridev.makkalmart.viewmodel.MakkalMartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(productId: Int?, navController: NavHostController, viewModel: MakkalMartViewModel) {
    val product = productId?.let { viewModel.getProductById(it) }

    Scaffold (topBar = { TopAppBar(title = { Text("Product Details",color = MaterialTheme.colorScheme.onPrimary) },
        navigationIcon = {
            IconButton (onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.Close, contentDescription = "close", tint = MaterialTheme.colorScheme.onPrimary)
            }
        }
        , colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
       )
    },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->

        if (product == null) {
            Text(text = "Product Not Found", modifier = Modifier.padding(innerPadding))
        } else {
            Column(modifier = Modifier.padding(innerPadding).padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Card( modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                    AsyncImage(model = "${product.image}",contentDescription = product.title, modifier = Modifier.fillMaxWidth().height(300.dp))
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(text = product.title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)

                        Text(text = "₹ ${product.price}", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                        Row(){
                            Text(text = "⭐ ${product.ratingRate}", fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "(${product.ratingCount} reviews)")
                        }
                        AssistChip(onClick = {}, label = { Text(product.category) })
                    }

                    Card( modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(14.dp),verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(text = "Product Information", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text("Category : ${product.category}")
                            Text("Rating : ${product.ratingRate}")
                            Text("Reviews : ${product.ratingCount}")
                            Text(text = "Description", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold,)
                            Text(text = product.description,modifier = Modifier.weight(1f).verticalScroll(rememberScrollState()))

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(modifier = Modifier.weight(1f), onClick = {
                                        navController.navigate("edit_product/${product.id}") }) {
                                    Text("Edit")
                                }
                                Button(modifier = Modifier.weight(1f), onClick = {
                                    viewModel.deleteProduct(product)
                                    navController.popBackStack()
                                }) {
                                    Text("Delete")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}