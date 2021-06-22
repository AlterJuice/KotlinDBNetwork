package com.edu.kotlindbnetwork

class HappySingleton {

    companion object {
        private var countInits: Int = 0
        private lateinit var happySingleton: HappySingleton

        fun getInstance(): HappySingleton {
            if (!::happySingleton.isInitialized) {
                synchronized(happySingleton) {
                    if (!::happySingleton.isInitialized) {
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