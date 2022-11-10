package com.example.gorest.di

import com.example.gorest.data.remote.GoRestApi
import com.example.gorest.data.remote.repository.GoRestRepositoryImpl
import com.example.gorest.util.Constants.BASE_URL
import com.example.gorest.util.Constants.TOKEN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor {
            val token: String = TOKEN
            val builder = it.request()
                .newBuilder()

            builder.addHeader("Authorization", "Bearer $token")
            val request = builder.build()
            return@Interceptor it.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClientBuilder(
        headerInterceptor: Interceptor
    ): OkHttpClient.Builder {
        val builder = OkHttpClient()
            .newBuilder()
            .addInterceptor(headerInterceptor)
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            //Using a logger, log the total number of pages from the previous request.
            //X-Pagination-Pages is logged by okhttp interceptor.
            level = HttpLoggingInterceptor.Level.BODY
        })
        return builder
    }

    @Singleton
    @Provides
    fun providesGoRestApi(
        okHttpClientBuilder: OkHttpClient.Builder,
    ): GoRestApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .baseUrl(BASE_URL)
            .build()
            .create(GoRestApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGoRestRepository(
        api: GoRestApi
    ) = GoRestRepositoryImpl(api)

}