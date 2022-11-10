package com.example.gorest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GoRestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}