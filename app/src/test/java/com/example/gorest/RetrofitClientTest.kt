package com.example.gorest

import com.example.gorest.data.remote.GoRestApi
import com.example.gorest.data.remote.repository.GoRestRepositoryImpl
import com.example.gorest.data.remote.responses.UsersResponseItem
import com.example.gorest.util.Response
import org.junit.Test
import javax.inject.Inject

class RetrofitClientTest @Inject constructor(
    private val api: GoRestApi
) {

    private val service = GoRestRepositoryImpl(api)

    @Test
    suspend fun testGetUsers() {
        val response = service.getUsers(3)

        assert(response.data != null)
    }

    @Test
    suspend fun testGetUser() {
        val response = service.getUser(5555)

        assert(response.data != null)
    }

    @Test
    suspend fun testUpdateUser() {
        val usersResponseItem = UsersResponseItem(
            "test@test.test",
            "test",
            5554,
            "test",
            "test"
        )
        val response = service.updateUser(5554, usersResponseItem)

        assert(response.data != null)
    }

    @Test
    suspend fun testDeleteUser() {
        val response = service.deleteUser(5554)

        assert(response.data != null)
    }


}