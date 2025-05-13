package com.agro.kilimo.ui.theme.screens.productse

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.agro.kilimo.data.productviewmodel
import com.agro.kilimo.models.Product
import com.agro.kilimo.navigation.ROUT_UPDATEPRODUCTSCREEN

@Composable
fun ViewProductsScreen(navController: NavHostController) {
    val context = LocalContext.current
    val productRepository = productviewmodel(navController, context)
    val emptyProduct = remember { mutableStateOf(Product("", "", "", "")) }
    val products = remember { mutableStateListOf<Product>() } // Use mutableStateListOf for Compose
    val isLoading by productRepository.loading

    // Fetch products
    LaunchedEffect(Unit) {
        productRepository.viewProducts(emptyProduct, products)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your Products",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            if (products.isEmpty()) {
                Text(
                    text = "No products available",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(products) { product ->
                        ProductCard(
                            product = product,
                            onUpdateClick = {
                                Log.d("ViewProductsScreen", "Navigating to $ROUT_UPDATEPRODUCTSCREEN/${product.id}")
                                if (product.id.isBlank()) {
                                    Log.e("ViewProductsScreen", "Empty product ID for product: $product")
                                    Toast.makeText(context, "Invalid product ID", Toast.LENGTH_SHORT).show()
                                    return@ProductCard
                                }
                                try {
                                    navController.navigate("$ROUT_UPDATEPRODUCTSCREEN/${product.id}")
                                } catch (e: IllegalArgumentException) {
                                    Log.e("ViewProductsScreen", "Navigation error: ${e.message}")
                                    Toast.makeText(context, "Navigation error: ${e.message}", Toast.LENGTH_SHORT).show()
                                }
                            },
                            onDeleteClick = {
                                productRepository.deleteProduct(product.id)
                            }
                        )
                    }
                }
            }
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(48.dp),
                color = Color(0xFF4CAF50)
            )
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onUpdateClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onUpdateClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = product.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF333333)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Quantity: ${product.quantity}",
                fontSize = 16.sp,
                color = Color(0xFF666666)
            )
            Text(
                text = "Price: ${product.price}",
                fontSize = 16.sp,
                color = Color(0xFF666666)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onUpdateClick) {
                    Text("Update", color = Color(0xFF4CAF50), fontWeight = FontWeight.Medium)
                }
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(onClick = onDeleteClick) {
                    Text("Delete", color = Color.Red, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}