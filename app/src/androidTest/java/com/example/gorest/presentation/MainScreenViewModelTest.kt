package com.example.gorest.presentation

import androidx.lifecycle.Observer
import com.example.gorest.data.remote.GoRestApi
import com.example.gorest.data.remote.repository.GoRestRepositoryImpl
import com.example.gorest.data.remote.responses.UsersResponse
import com.example.gorest.util.Response
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject


@RunWith(MockitoJUnitRunner::class)
class MainScreenViewModelTest {

    @Inject
    private lateinit var api: GoRestApi

    private lateinit var goRestRepositoryImpl: GoRestRepositoryImpl
    private lateinit var viewModel: MainScreenViewModel

    @Mock
    private lateinit var apiEmployeeObserver: Observer<Response<UsersResponse>?>

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        goRestRepositoryImpl = GoRestRepositoryImpl(api)

        viewModel = MainScreenViewModel(goRestRepositoryImpl)
        viewModel.loadUsers()

        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Test
    fun readSuccessJson(){
        val reader = MockResponseFileReader("success_response.json")
        assertNotNull(reader.content)
    }

}