package com.edu.kotlindbnetwork.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edu.kotlindbnetwork.repo.UserRepo
import com.edu.kotlindbnetwork.viewmodels.UserProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    internal abstract fun bindUserProfileViewModel(userProfileViewModel: UserProfileViewModel): UserProfileViewModel

    @Binds
    internal abstract fun bindUserProfileViewModel(factory: ViewModelProvider.Factory): ViewModelProvider.Factory
    // https://coderoad.ru/44712248/%D0%98%D0%BD%D1%8A%D0%B5%D0%BA%D1%86%D0%B8%D1%8F-ViewModel-%D1%81-%D0%BF%D0%BE%D0%BC%D0%BE%D1%89%D1%8C%D1%8E-Dagger-2-Kotlin-ViewModel
    // https://github.com/bubelov/coin-map-android/commit/56629d75569bc2937889b71299c21b9579ad96c4
}


