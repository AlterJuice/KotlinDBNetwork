package com.edu.kotlindbnetwork;

import android.app.Application

class App : Application() {


    companion object {
        private lateinit var component: AppComponent

        fun getComponent(): AppComponent{
            return component
        }
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().repoModule(RepoModule(this)).build()
    }

}
