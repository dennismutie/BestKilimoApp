package com.agro.kilimo.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.agro.kilimo.models.Product
import com.agro.kilimo.navigation.ROUT_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class productviewmodel(var navController: NavHostController, var context: Context) {
    private var authRepository: AuthViewModel = AuthViewModel(navController, context)
    var loading = mutableStateOf(false)

    init {
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUT_LOGIN)
        }
    }

    fun saveProduct(productName: String, productQuantity: String, productPrice: String) {
        if (productName.isBlank() || productQuantity.isBlank() || productPrice.isBlank()) {
            Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }
        if (productQuantity.toIntOrNull() == null || productPrice.toDoubleOrNull() == null) {
            Toast.makeText(context, "Invalid quantity or price", Toast.LENGTH_SHORT).show()
            return
        }

        val id = System.currentTimeMillis().toString()
        val productData = Product(productName, productQuantity, productPrice, id)
        val productRef = FirebaseDatabase.getInstance().getReference().child("Products/$id")

        loading.value = true
        productRef.setValue(productData).addOnCompleteListener {
            loading.value = false
            if (it.isSuccessful) {
                Toast.makeText(context, "Product saved successfully", Toast.LENGTH_SHORT).show()
                Log.d("ProductViewModel", "Saved product: $productData")
            } else {
                Log.e("ProductViewModel", "Save error: ${it.exception?.message}")
                Toast.makeText(context, "Error: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun viewProducts(
        product: MutableState<Product>,
        products: MutableList<Product> // Changed to standard MutableList
    ): MutableList<Product> {
        val ref = FirebaseDatabase.getInstance().getReference().child("Products")
        loading.value = true
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                loading.value = false
                products.clear()
                if (!snapshot.exists()) {
                    Log.d("ProductViewModel", "No products found in Firebase")
                    Toast.makeText(context, "No products available", Toast.LENGTH_SHORT).show()
                    return
                }
                for (snap in snapshot.children) {
                    val value = snap.getValue(Product::class.java)
                    if (value != null) {
                        Log.d("ProductViewModel", "Fetched product: $value")
                        product.value = value
                        products.add(value)
                    } else {
                        Log.w("ProductViewModel", "Failed to parse product: ${snap.key}")
                    }
                }
                Log.d("ProductViewModel", "Total products fetched: ${products.size}")
            }

            override fun onCancelled(error: DatabaseError) {
                loading.value = false
                Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                Log.e("ProductViewModel", "Fetch error: ${error.message}")
            }
        })
        return products
    }

    fun deleteProduct(id: String) {
        val delRef = FirebaseDatabase.getInstance().getReference().child("Products/$id")
        loading.value = true
        delRef.removeValue().addOnCompleteListener {
            loading.value = false
            if (it.isSuccessful) {
                Toast.makeText(context, "Product deleted successfully", Toast.LENGTH_SHORT).show()
                Log.d("ProductViewModel", "Deleted product with id: $id")
            } else {
                Log.e("ProductViewModel", "Delete error: ${it.exception?.message}")
                Toast.makeText(context, "Error: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateProduct(
        name: String,
        quantity: String,
        price: String,
        id: String,
        onComplete: (success: Boolean, errorMessage: String?) -> Unit
    ) {
        if (name.isBlank() || quantity.isBlank() || price.isBlank() || id.isBlank()) {
            Log.e("ProductViewModel", "Invalid input: name=$name, quantity=$quantity, price=$price, id=$id")
            onComplete(false, "All fields are required")
            return
        }
        if (quantity.toIntOrNull() == null || price.toDoubleOrNull() == null) {
            Log.e("ProductViewModel", "Invalid quantity or price: quantity=$quantity, price=$price")
            onComplete(false, "Invalid quantity or price")
            return
        }

        val updateRef = FirebaseDatabase.getInstance().getReference().child("Products/$id")
        val updateData = Product(name, quantity, price, id)

        loading.value = true
        updateRef.setValue(updateData).addOnCompleteListener {
            loading.value = false
            if (it.isSuccessful) {
                Log.d("ProductViewModel", "Updated product: $updateData")
                onComplete(true, null)
            } else {
                Log.e("ProductViewModel", "Update error: ${it.exception?.message}")
                onComplete(false, it.exception?.message)
            }
        }
    }
}