package com.edu.kotlindbnetwork

import android.util.Log

class HappySingleton {

    companion object{
        private var countInits: Int = 0
        private lateinit var happySingleton: HappySingleton

        fun getInstance(): HappySingleton{
            if (!::happySingleton.isInitialized) {
                synchronized(happySingleton) {
                    if (!::happySingleton.isInitialized) {
                        Log.d("HAPPYTAG", "Created new Happy")
                        happySingleton = HappySingleton()
                        countInits++
                    }
                }
            }
            return happySingleton
        }
    }

    override fun toString(): String {
        return "HappySingleton<$countInits>"
    }
}