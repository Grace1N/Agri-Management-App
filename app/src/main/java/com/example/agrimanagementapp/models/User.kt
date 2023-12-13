package com.example.agrimanagementapp.models

class User {
    var name:String=""
    var email:String=""
    var password:String=""
    var idNumber:String=""

    constructor(name: String, email: String, password: String, idNumber: String) {
        this.name = name
        this.email = email
        this.password = password
        this.idNumber = idNumber
    }
    constructor()
}