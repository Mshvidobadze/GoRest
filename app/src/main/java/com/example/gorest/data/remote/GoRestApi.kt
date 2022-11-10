package com.example.gorest.data.remote

import com.example.gorest.data.remote.responses.UsersResponse
import com.example.gorest.data.remote.responses.UsersResponseItem
import com.example.gorest.util.Constants.VERSION_2
import retrofit2.http.*

interface GoRestApi {

    @GET("$VERSION_2/users")
    suspend fun getUsers(
        @Query("page") page: Int
    ): UsersResponse

    @GET("$VERSION_2/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): UsersResponseItem

    @PUT("$VERSION_2/users/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body user: UsersResponseItem
    ): UsersResponseItem

    @DELETE("$VERSION_2/users/{id}")
    suspend fun deleteUser(
        @Path("id") id: Int
    )

}