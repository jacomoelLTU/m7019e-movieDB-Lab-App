package com.example.themoviedbv24

import android.app.Application
import com.example.themoviedbv24.database.AppContainer
import com.example.themoviedbv24.database.DefaultAppContainer

class MovieDBApplication : Application() {
    lateinit var container : AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}