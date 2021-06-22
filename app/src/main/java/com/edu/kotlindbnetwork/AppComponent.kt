package com.edu.kotlindbnetwork

import com.edu.kotlindbnetwork.data.db.Database
import com.edu.kotlindbnetwork.data.network.APIService
import com.edu.kotlindbnetwork.modules.NetworkModule
import com.edu.kotlindbnetwork.modules.RepoModule
import com.edu.kotlindbnetwork.modules.ViewModelModule
import com.edu.kotlindbnetwork.repo.UserRepo
import com.edu.kotlindbnetwork.ui.MainActivity
import com.edu.kotlindbnetwork.ui.UserListFragment
import com.edu.kotlindbnetwork.ui.UserProfileFragment
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Named

@Component(modules = [RepoModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(target: MainActivity)
    fun inject(target: UserListFragment)
    fun inject(target: UserProfileFragment)

    fun getUserRepo(): UserRepo
    fun getDatabase(): Database
    @Named(Consts.MODULE_TAG_API_SERVICE) fun getApiService(): APIService
    fun getRetrofit(): Retrofit

}
