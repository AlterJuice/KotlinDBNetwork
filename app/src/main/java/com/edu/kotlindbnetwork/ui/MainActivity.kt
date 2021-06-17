package com.edu.kotlindbnetwork.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edu.kotlindbnetwork.DiUtil
import com.edu.kotlindbnetwork.R


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DiUtil.init(this)
    }
}