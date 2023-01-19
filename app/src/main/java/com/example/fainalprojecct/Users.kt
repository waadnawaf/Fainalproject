package com.example.fainalprojecct

import java.io.Serializable

data class Users(
    var pk: String,
    var username:String,
    var email:String,
    var password:String?="",
): Serializable
