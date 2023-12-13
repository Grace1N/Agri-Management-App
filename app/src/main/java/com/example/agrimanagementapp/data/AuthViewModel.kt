package com.example.agrimanagementapp.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.agrimanagementapp.models.User
import com.example.agrimanagementapp.navigation.HOME_URL
import com.example.agrimanagementapp.navigation.LOGIN_URL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.FirebaseDatabase


class AuthViewModel (
    var navController:NavHostController,
    var context:Context){
        var mAuth:FirebaseAuth
        var progress:ProgressDialog

        init {
            mAuth=FirebaseAuth.getInstance()
            progress= ProgressDialog(context)
            progress.setTitle("Loading")
            progress.setMessage("Please wait....")
        }
        fun signup( name:String,  email:String, password:String){
            progress.show()
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                var userId = mAuth.currentUser!!.uid
                var userProfile= User(name,email, password,userId)
                var usersRef = FirebaseDatabase.getInstance().getReference().child("Users/$userId")
                usersRef.setValue(userProfile).addOnCompleteListener {
                    progress.dismiss()
                    Toast.makeText(this.context, "WORKED", Toast.LENGTH_SHORT).show()
                    if (it.isSuccessful){
                        Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                        navController.navigate(LOGIN_URL)
                    }else{
                        Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        fun login(email: String,password: String){
            progress.show()
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful){
                    Toast.makeText(this.context,"Success",Toast.LENGTH_SHORT).show()
                    navController.navigate(HOME_URL)
                }else{
                    Toast.makeText(this.context,"Error",Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun logout(){
            mAuth.signOut()
            navController.navigate(LOGIN_URL)
        }
        fun isLoggedIn():Boolean = mAuth.currentUser != null

    }



