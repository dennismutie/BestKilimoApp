package com.agro.kilimo.ui.theme.screens.productse

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.agro.kilimo.data.productviewmodel
import com.agro.kilimo.models.Product
import com.agro.kilimo.navigation.ROUT_VIEWPRODUCTSCREEN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun UpdateProductsScreen(navController: NavHostController, productId: String) {
    val context = LocalContext.current
    val productRepository = productviewmodel(navController, context)
    var productName by remember { mutableStateOf(TextFieldValue("")) }
    var productQuantity by remember { mutableStateOf(TextFieldValue("")) }
    var productPrice by remember { mutableStateOf(TextFieldValue("")) }
    var isDataLoaded by remember { mutableStateOf(false) }
    val isLoading by productRepository.loading

    // Validate productId and fetch data
    LaunchedEffect(productId) {
        if (productId.isBlank()) {
            Log.e("UpdateProductsScreen", "Empty or invalid productId: $productId")
            Toast.makeText(context, "Invalid product ID", Toast.LENGTH_LONG).show()
            try {
                navController.navigate(ROUT_VIEWPRODUCTSCREEN)
            } catch (e: Exception) {
                Log.e("UpdateProductsScreen", "Navigation error on invalid productId: ${e.message}", e)
                Toast.makeText(context, "Navigation error: ${e.message}", Toast.LENGTH_LONG).show()
            }
            return@LaunchedEffect
        }

        Log.d("UpdateProductsScreen", "Fetching product data for ID: $productId")
        try {
            val currentDataRef = FirebaseDatabase.getInstance().getReference().child("Products/$productId")
            currentDataRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val product = snapshot.getValue(Product::class.java)
                        if (product != null) {
                            productName = TextFieldValue(product.name)
                            productQuantity = TextFieldValue(product.quantity)
                            productPrice = TextFieldValue(product.price)
                            isDataLoaded = true
                            Log.d("UpdateProductsScreen", "Fetched product: $product")
                        } else {
                            Log.w("UpdateProductsScreen", "Product data is null for ID: $productId")
                            Toast.makeText(context, "Product not found", Toast.LENGTH_LONG).show()
                            try {
                                navController.navigate(ROUT_VIEWPRODUCTSCREEN)
                            } catch (e: Exception) {
                                Log.e("UpdateProductsScreen", "Navigation error on null product: ${e.message}", e)
                            }
                        }
                    } else {
                        Log.w("UpdateProductsScreen", "No data found for productId: $productId")
                        Toast.makeText(context, "Product not found", Toast.LENGTH_LONG).show()
                        try {
                            navController.navigate(ROUT_VIEWPRODUCTSCREEN)
                        } catch (e: Exception) {
                            Log.e("UpdateProductsScreen", "Navigation error on no data: ${e.message}", e)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("UpdateProductsScreen", "Firebase fetch error: ${error.message}")
                    Toast.makeText(context, "Fetch error: ${error.message}", Toast.LENGTH_LONG).show()
                    try {
                        navController.navigate(ROUT_VIEWPRODUCTSCREEN)
                    } catch (e: Exception) {
                        Log.e("UpdateProductsScreen", "Navigation error on fetch cancel: ${e.message}", e)
                    }
                }
            })
        } catch (e: Exception) {
            Log.e("UpdateProductsScreen", "Unexpected error fetching data: ${e.message}", e)
            Toast.makeText(context, "Error fetching data: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        if (!isDataLoaded && !isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(48.dp),
                color = Color(0xFF4CAF50)
            )
        } else if (isDataLoaded) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Update Product",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 24.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = productName,
                            onValueChange = { productName = it },
                            label = { Text("Product Name *") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF4CAF50),
                                focusedLabelColor = Color(0xFF4CAF50),
                                unfocusedBorderColor = Color.Gray,
                                unfocusedLabelColor = Color.Gray
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = productQuantity,
                            onValueChange = { productQuantity = it },
                            label = { Text("Product Quantity *") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF4CAF50),
                                focusedLabelColor = Color(0xFF4CAF50),
                                unfocusedBorderColor = Color.Gray,
                                unfocusedLabelColor = Color.Gray
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = productPrice,
                            onValueChange = { productPrice = it },
                            label = { Text("Product Price *") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF4CAF50),
                                focusedLabelColor = Color(0xFF4CAF50),
                                unfocusedBorderColor = Color.Gray,
                                unfocusedLabelColor = Color.Gray
                            )
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = {
                                Log.d("UpdateProductsScreen", "Update button clicked for productId: $productId")
                                try {
                                    if (productId.isBlank()) {
                                        Log.e("UpdateProductsScreen", "Invalid productId: $productId")
                                        Toast.makeText(context, "Invalid product ID", Toast.LENGTH_LONG).show()
                                        return@Button
                                    }
                                    if (productName.text.isBlank() || productQuantity.text.isBlank() || productPrice.text.isBlank()) {
                                        Log.w("UpdateProductsScreen", "Validation failed: Empty fields")
                                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_LONG).show()
                                        return@Button
                                    }
                                    if (productQuantity.text.toIntOrNull() == null || productPrice.text.toDoubleOrNull() == null) {
                                        Log.w("UpdateProductsScreen", "Invalid quantity or price: quantity=${productQuantity.text}, price=${productPrice.text}")
                                        Toast.makeText(context, "Invalid quantity or price", Toast.LENGTH_LONG).show()
                                        return@Button
                                    }

                                    Log.d("UpdateProductsScreen", "Calling updateProduct with name=${productName.text}, quantity=${productQuantity.text}, price=${productPrice.text}")
                                    productRepository.updateProduct(
                                        productName.text.trim(),
                                        productQuantity.text.trim(),
                                        productPrice.text.trim(),
                                        productId,
                                        onComplete = { success: Boolean, errorMessage: String? ->
                                            try {
                                                if (success) {
                                                    Log.d("UpdateProductsScreen", "Update successful, navigating to $ROUT_VIEWPRODUCTSCREEN")
                                                    Toast.makeText(context, "Product updated successfully", Toast.LENGTH_LONG).show()
                                                    navController.navigate(ROUT_VIEWPRODUCTSCREEN)
                                                } else {
                                                    Log.e("UpdateProductsScreen", "Update failed: $errorMessage")
                                                    Toast.makeText(context, "Error: ${errorMessage ?: "Unknown error"}", Toast.LENGTH_LONG).show()
                                                }
                                            } catch (e: Exception) {
                                                Log.e("UpdateProductsScreen", "Error in callback: ${e.message}", e)
                                                Toast.makeText(context, "Callback error: ${e.message}", Toast.LENGTH_LONG).show()
                                            }
                                        }
                                    )
                                } catch (e: Exception) {
                                    Log.e("UpdateProductsScreen", "Unexpected error during update: ${e.message}", e)
                                    Toast.makeText(context, "Unexpected error: ${e.message ?: "Unknown error"}", Toast.LENGTH_LONG).show()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4CAF50),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Update Product", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        }
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