package com.example.gorest.data.remote.repository

import com.example.gorest.data.remote.responses.UsersResponse
import com.example.gorest.data.remote.responses.UsersResponseItem
import com.example.gorest.util.Response

interface GoRestRepository {

    suspend fun getUsers(page: Int): Response<UsersResponse>

    suspend fun getUser(id: Int): Response<UsersResponseItem>

    suspend fun updateUser(id: Int, user: UsersResponseItem): Response<UsersResponseItem>

    suspend fun deleteUser(id: Int): Response<Unit>

}