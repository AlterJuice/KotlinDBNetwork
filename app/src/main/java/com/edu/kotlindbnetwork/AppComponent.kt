package com.edu.kotlindbnetwork;

import com.edu.kotlindbnetwork.db.Database;
import com.edu.kotlindbnetwork.repo.UserRepo
import com.edu.kotlindbnetwork.ui.MainActivity
import com.edu.kotlindbnetwork.ui.UserListFragment
import com.edu.kotlindbnetwork.ui.UserProfileFragment

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = [RepoModule::class])
interface AppComponent {

    fun injectsMainActivity(mainActivity: MainActivity)
    fun injectsUserListFragment(userListFragment: UserListFragment)
    fun injectsUserProfileFragment(userProfileFragment: UserProfileFragment)

    fun getUserRepo(): UserRepo
    fun getDatabase(): Database
    fun getApiService(): APIService
    fun getRetrofit(): Retrofit

}
