package com.example.gorest.presentation

import android.os.Build
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gorest.data.remote.repository.GoRestRepositoryImpl
import com.example.gorest.data.remote.responses.UsersResponseItem
import com.example.gorest.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repositoryImpl: GoRestRepositoryImpl
) : ViewModel() {

    private var page = 3
    private var userId = 5555

    var usersList = mutableStateOf<List<UsersResponseItem>>(listOf())
    var error = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        loadUsers()
        getUser()
    }

    fun loadUsers() {
        viewModelScope.launch {
            //Retrieve page 3 of the list of all users.
            isLoading.value = true
            val result = repositoryImpl.getUsers(page)
            when(result){
                is Response.Success -> {
                    val userEntries = result.data?.map {
                        UsersResponseItem(it.email, it.gender, it.id, it.name, it.status)
                    }
                    error.value = ""
                    isLoading.value = false

                    if (userEntries != null) {
                        //Using a logger, log the total number of pages from the previous request.
                        //X-Pagination-Pages is logged by okhttp interceptor.
                        usersList.value = userEntries
                        //Sort the retrieved user list by name.
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Collections.sort(usersList.value, Comparator.comparing(UsersResponseItem::name))
                        }
                        //After sorting, log the name of the last user.
                        var user = usersList.value.last()
                        Log.e("Last user's name", user.name)
                        //Update that user's name to a new value and use the correct http method to save it.
                        user.name = "new user name"
                        updateUser(user)

                    }
                }
                is Response.Error -> {
                    Log.e("ERROR", result.message.toString())
                }
                is Response.Loading -> {
                    Log.e("LOADING", "users")
                }
            }
        }
    }

    private fun getUser() {
        //Attempt to retrieve a nonexistent user with ID 5555.
        viewModelScope.launch {
            val result = repositoryImpl.getUser(userId)
            when(result){
                is Response.Success -> {
                    val user = result.data
                    Log.e("User", user.toString())
                }
                is Response.Error -> {
                    //Log the resulting http response code.
                    Log.e("ERROR", result.message.toString())
                }
                is Response.Loading -> {
                    Log.e("LOADING", "users")
                }
            }
        }
    }

    private fun updateUser(user: UsersResponseItem) {
        viewModelScope.launch {
            val result = repositoryImpl.updateUser(user.id, user)
            when(result){
                is Response.Success -> {
                    val updatedUser = result.data
                    Log.e("User", updatedUser.toString())
                    //Delete that user.
                    deleteUser(user.id)
                }
                is Response.Error -> {
                    Log.e("ERROR", result.message.toString())
                }
                is Response.Loading -> {
                    Log.e("LOADING", "users")
                }
            }
        }
    }

    private fun deleteUser(id: Int) {
        viewModelScope.launch {
            val result = repositoryImpl.deleteUser(id)
            when(result){
                is Response.Success -> {
                    Log.e("User", result.toString())
                }
                is Response.Error -> {
                    Log.e("ERROR", result.message.toString())
                }
                is Response.Loading -> {
                    Log.e("LOADING", "users")
                }
            }
        }
    }
}