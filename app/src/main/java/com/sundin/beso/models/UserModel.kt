package com.sundin.beso.models

data class UserModel(
    val userProfileImage: Int,
    val userFirstName: String,
    val userLastName: String?,
    val userEmail: String,
    var friendsList: ArrayList<String>,
    var interestsList: ArrayList<String>
)
