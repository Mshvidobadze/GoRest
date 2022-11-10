package com.example.gorest.remote.repository

import androidx.compose.runtime.mutableStateOf
import com.example.gorest.data.remote.repository.GoRestRepository
import com.example.gorest.data.remote.responses.UsersResponse
import com.example.gorest.data.remote.responses.UsersResponseItem
import com.example.gorest.util.Response

class FakeGoRestRepository: GoRestRepository {

    private var usersResponse: UsersResponse = UsersResponse()

    private var usersList = mutableStateOf<List<UsersResponseItem>>(listOf())

    override suspend fun getUsers(page: Int): Response<UsersResponse> {
        return Response.Success(usersResponse)
    }

    override suspend fun getUser(id: Int): Response<UsersResponseItem> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(id: Int, user: UsersResponseItem): Response<UsersResponseItem> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(id: Int): Response<Unit> {
        TODO("Not yet implemented")
    }

}