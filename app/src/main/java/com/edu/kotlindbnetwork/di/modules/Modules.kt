package com.edu.kotlindbnetwork.di.modules

import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.viewmodels.UserListViewModel
import com.edu.kotlindbnetwork.viewmodels.UserProfileViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object Modules {
    private val networkModule = module {
        single { NetworkModule.provideRetrofit() }
        single { NetworkModule.provideApiService(get()) }
    }

    private val repoModule = module {
        // get context via androidApplication() or just get() extension function
        single { RepoModule.provideDatabase(androidApplication()) }
        single(named(Consts.MODULE_TAG_REPO_NETWORK)) { RepoModule.provideRepoNetwork(get()) }
        single(named(Consts.MODULE_TAG_REPO_DATABASE)) { RepoModule.provideRepoDatabase(get()) }
        single(named(Consts.MODULE_TAG_REPO_BOTH)) {
            RepoModule.provideUserRepo(
                get(named(Consts.MODULE_TAG_REPO_NETWORK)),
                get(named(Consts.MODULE_TAG_REPO_DATABASE))
            )
        }
    }

    private val viewModelModule = module {
        /*A scope is a context with a fixed duration of time, in which an object exists.
         When the scope ends, any objects bound under that scope cannot be injected again.
         To better have an image of that, think that a scope is like a box: a space where
         you put things and throw it when you don’t need it anymore.
         How do we use the new Scope API?
         https://medium.com/koin-developers/ready-for-koin-2-0-2722ab59cac3
         https://medium.com/mobile-app-development-publication/kotlin-koin-scope-illustrated-3bfa6c7ae98
         */

        viewModel(named(Consts.MODULE_VIEW_MODEL_USER_LIST)) {
            UserListViewModel(get(named(Consts.MODULE_TAG_REPO_BOTH)))
        }

        viewModel(named(Consts.MODULE_VIEW_MODEL_USER_PROFILE)) { (userId: String) ->
            UserProfileViewModel(
                userRepo = get(named(Consts.MODULE_TAG_REPO_BOTH)),
                userId = userId
            )
        }
    }

    val allModules = listOf(networkModule, repoModule, viewModelModule)

}