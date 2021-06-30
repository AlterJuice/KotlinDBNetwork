package com.edu.kotlindbnetwork.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(UserListFragment.newInstance(), Consts.FRAGMENT_USER_LIST_TAG)
    }

    fun replaceFragment(fragment: Fragment, tag: String){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFragmentContainer, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    fun setBarSubtitle(text: String) {
        supportActionBar?.subtitle = text
    }

    override fun onBackPressed() {
        setBarSubtitle("")
        super.onBackPressed()
    }
}
