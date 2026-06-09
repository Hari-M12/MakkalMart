package com.haridev.makkalmart.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.haridev.makkalmart.data.model.Product
import com.haridev.makkalmart.viewmodel.MakkalMartViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormScreen(productId: Int? = null,navController: NavController,viewModel: MakkalMartViewModel){
    val context = LocalContext.current
    val existingProduct = productId?.let { viewModel.getProductById(it) }
    var title by rememberSaveable { mutableStateOf(existingProduct?.title ?: "") }
    var price by rememberSaveable { mutableStateOf(existingProduct?.price?.toString()?:"") }
    var description by rememberSaveable { mutableStateOf(existingProduct?.description?:"") }
    var category by rememberSaveable { mutableStateOf(existingProduct?.category ?: "") }
    var image by rememberSaveable { mutableStateOf(existingProduct?.image ?:"") }
    var ratingRate by rememberSaveable { mutableStateOf(existingProduct?.ratingRate?.toString() ?:"") }
    var ratingCount by rememberSaveable { mutableStateOf(existingProduct?.ratingCount?.toString() ?:"") }


    Scaffold(
        topBar = { TopAppBar(title = {
            Text(
                if(productId == null) "Add Product"
                else "Update Product",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }, navigationIcon = {
            IconButton (onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.Close, contentDescription = "close", tint = MaterialTheme.colorScheme.onPrimary)
            }
        },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary))
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding).padding(16.dp).verticalScroll(
                rememberScrollState()
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(value = title, onValueChange = { title = it },
                label = { Text("Title") }, modifier = Modifier.fillMaxWidth())

            OutlinedTextField(value = price, onValueChange = { price = it },
                label = { Text("Price") }, modifier = Modifier.fillMaxWidth())

            OutlinedTextField(value = category, onValueChange = { category = it },
                label = { Text("Category") }, modifier = Modifier.fillMaxWidth())

            OutlinedTextField(value = image, onValueChange = { image = it }, label = { Text("Image URL") }, modifier = Modifier.fillMaxWidth())

            OutlinedTextField(value = description, onValueChange = { description = it },
                label = { Text("Description") }, modifier = Modifier.fillMaxWidth(), minLines = 4)
            OutlinedTextField(value = ratingRate, onValueChange = { ratingRate = it },
                label = { Text("Rating") }, modifier = Modifier.fillMaxWidth())

            OutlinedTextField(value = ratingCount, onValueChange = { ratingCount = it }, label = { Text("Review Count") },
                modifier = Modifier.fillMaxWidth())

            Button(onClick = {

                if (title.isBlank()) {
                    Toast.makeText(context, "Title is required", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (price.isBlank() || price.toDoubleOrNull() == null) {
                    Toast.makeText(context, "Valid price is required", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (category.isBlank()) {
                    Toast.makeText(context, "Category is required", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (image.isBlank()) {
                    Toast.makeText(context, "Image URL is required", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (description.isBlank()) {
                    Toast.makeText(context, "Description is required", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if (ratingRate.isBlank() || ratingRate.toDoubleOrNull() == null) {
                    Toast.makeText(context, "Valid ratingrate is required", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if (ratingCount.isBlank() || ratingCount.toDoubleOrNull() == null) {
                    Toast.makeText(context, "Valid ratingcount is required", Toast.LENGTH_SHORT,).show()
                    return@Button
                }
                val product = Product(
                    id = existingProduct?.id ?:0,
                    title = title,
                    price = price.toDoubleOrNull() ?: 0.0,
                    description = description,
                    category = category,
                    image = image,
                    ratingRate = ratingRate.toDoubleOrNull() ?: 0.0,
                    ratingCount = ratingCount.toIntOrNull() ?: 0
                )

                if (productId == null) {
                    viewModel.insertProduct(product)
                } else {
                    viewModel.updateProduct(product)
                }
                navController.popBackStack()
            },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (productId == null)
                    "Save Product"
                else
                    "Update Product")
            }
        }
    }

}