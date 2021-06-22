package com.edu.kotlindbnetwork

import android.app.Application
import com.edu.kotlindbnetwork.di.AppComponent
import com.edu.kotlindbnetwork.di.DaggerAppComponent
import com.edu.kotlindbnetwork.di.modules.NetworkModule
import com.edu.kotlindbnetwork.di.modules.RepoModule

class App : Application() {
    companion object {
        private lateinit var component: AppComponent
        fun getComponent(): AppComponent {
            return component
        }
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .repoModule(RepoModule(this))
            .networkModule(NetworkModule())
            .build()
    }
}
