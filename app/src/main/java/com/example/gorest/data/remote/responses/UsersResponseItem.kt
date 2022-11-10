package com.example.gorest.data.remote.responses


import com.google.gson.annotations.SerializedName

data class UsersResponseItem(
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("status")
    val status: String
)