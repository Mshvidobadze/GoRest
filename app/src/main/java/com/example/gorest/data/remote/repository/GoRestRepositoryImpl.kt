package com.example.gorest.data.remote.repository

import android.util.Log
import com.example.gorest.data.remote.GoRestApi
import com.example.gorest.data.remote.responses.UsersResponse
import com.example.gorest.data.remote.responses.UsersResponseItem
import com.example.gorest.util.Response
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class GoRestRepositoryImpl @Inject constructor(
    private val api: GoRestApi
): GoRestRepository {

    override suspend fun getUsers(
        page: Int
    ): Response<UsersResponse> {
        val response = try {
            api.getUsers(page)
        } catch (e: Exception) {
            return Response.Error(e.message.toString())
        }
        return Response.Success(response)
    }

    override suspend fun getUser(
        id: Int
    ): Response<UsersResponseItem> {
        val response = try {
            api.getUser(id)
        } catch (e: Exception) {
            return Response.Error(e.message.toString())
        }
        return Response.Success(response)
    }

    override suspend fun updateUser(
        id: Int,
        user: UsersResponseItem
    ): Response<UsersResponseItem> {
        val response = try {
            api.updateUser(id, user)
        } catch (e: Exception) {
            return Response.Error(e.message.toString())
        }
        return Response.Success(response)
    }

    override suspend fun deleteUser(
        id: Int
    ): Response<Unit> {
        val response = try {
            api.deleteUser(id)
        } catch (e: Exception) {
            return Response.Error(e.message.toString())
        }
        return Response.Success(response)
    }

}