package com.example.agrimanagementapp.data

import android.app.ProgressDialog
import android.net.Uri
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.agrimanagementapp.models.Product
import com.example.agrimanagementapp.navigation.LOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask

class ProductViewModel (var navController:NavHostController,
    var context:Context) {

    var authViewModel: AuthViewModel
    var progress: ProgressDialog

    init {
        authViewModel = AuthViewModel(navController, context)
        if (!authViewModel.isLoggedIn()) {
            navController.navigate(LOGIN_URL)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait.....")
    }

    fun uploadProduct(name: String, quantity: String, price: String, filepath: Uri) {
        val productId = System.currentTimeMillis().toString()
        val storageRef = FirebaseStorage.getInstance().getReference().child("Products/$productId")
        progress.show()
        storageRef.putFile(filepath).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {

                storageRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var product = Product(name, quantity, price, imageUrl, productId)
                    Log.d("image_url", "uploadProduct: $imageUrl")
                    var databaseRef = FirebaseDatabase.getInstance().getReference().child("Products/$productId")
                    databaseRef.setValue(product).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }


            } else {
                Toast.makeText(this.context, "Upload Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun allProducts(
        product: MutableState<Product>,
        products: SnapshotStateList<Product>
    ): SnapshotStateList<Product> {
        progress.show()
        var ref = FirebaseDatabase.getInstance().getReference().child("Products")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                products.clear()
                for (snap in snapshot.children) {
                    var retrievedProduct = snap.getValue(Product::class.java)
                    product.value = retrievedProduct!!
                    products.add(retrievedProduct)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB Locked", Toast.LENGTH_SHORT).show()
            }
        })
        return products

    }

    fun deleteProduct(productId: String) {
        var ref = FirebaseDatabase.getInstance().getReference().child("Products/$productId")
        ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    fun viewProducts(
        product: MutableState<Product>,
        products: SnapshotStateList<Product>
    ): SnapshotStateList<Product> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Products")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                products.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Product::class.java)
                    product.value = value!!
                    products.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return products
    }

    fun updateProduct(name: String, quantity: String, price: String, id: String) {
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Products/$id")
        val productId = System.currentTimeMillis().toString()
        progress.show()
        val imageUrl = toString()
        var updateData = Product(name, quantity, price, imageUrl, productId)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun viewUploads(
        upload: MutableState<Product>,
        uploads: SnapshotStateList<Product>
    ): SnapshotStateList<Product> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Uploads")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                uploads.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Product::class.java)
                    upload.value = value!!
                    uploads.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return uploads
    }
}





