package com.edu.kotlindbnetwork.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edu.kotlindbnetwork.viewmodels.UserListViewModel
import com.edu.kotlindbnetwork.viewmodels.UserProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(FragmentComponent::class)
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindUserListViewModel(userListViewModel: UserListViewModel): ViewModel

// Используется своя ассистед фабрика для инджекта вью модели
//    @Binds
//    @IntoMap
//    @ViewModelKey(UserProfileViewModel::class)
//    abstract fun bindUserProfileViewModel(userProfileViewModel: UserProfileViewModel): ViewModel


    @Binds
    abstract fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}


