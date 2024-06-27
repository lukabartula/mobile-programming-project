package com.example.quickbasket

import android.app.Application
import com.example.quickbasket.data.AppContainer
import com.example.quickbasket.data.AppDataContainer


class UserApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
