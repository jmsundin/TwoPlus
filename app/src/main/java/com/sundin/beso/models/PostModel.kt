package com.sundin.beso.models

data class PostModel(
    val postImage: Int,
    val postTitle: String,
    val postDescription: String,
    val postTime: String,
    val postDate: String,
    val postLocation: String,
    val postDistanceFromUser: Float,
    var friendsGoing: ArrayList<String>,
    var othersGoing: ArrayList<String>
)
