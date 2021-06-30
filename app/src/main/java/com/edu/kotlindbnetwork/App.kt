package com.edu.kotlindbnetwork

import android.app.Application
import com.edu.kotlindbnetwork.di.modules.NetworkModule
import com.edu.kotlindbnetwork.di.modules.RepoModule
import com.edu.kotlindbnetwork.viewmodels.UserListViewModel
import com.edu.kotlindbnetwork.viewmodels.UserProfileViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class App : Application(){
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
                get(named(Consts.MODULE_TAG_REPO_DATABASE)))
                // RepoModule.provideRepoNetwork(get()),
                // RepoModule.provideRepoDatabase(get())
        }
    }

    private val viewModelModule = module {
        // scopes ?
        // scope<UserListFragment> {
        //     scoped {
        //         viewModel<UserListViewModel>(named(Consts.MODULE_VIEW_MODEL_USER_LIST)) {
        //             UserListViewModel(
        //                 get()
        //             )
        //         }
        //     }
        // }
        // scope<UserProfileFragment> {
        //     scoped {
        //         viewModel<UserProfileViewModel>(named(Consts.MODULE_VIEW_MODEL_USER_PROFILE)) { (userId: String) ->
        //             UserProfileViewModel(get(), userId)
        //         }
        //     }
        // }

        viewModel(named(Consts.MODULE_VIEW_MODEL_USER_LIST)) {
            UserListViewModel(get(named(Consts.MODULE_TAG_REPO_BOTH)))
        }

        viewModel(named(Consts.MODULE_VIEW_MODEL_USER_PROFILE)) { (userId: String) ->
            UserProfileViewModel(userRepo=get(named(Consts.MODULE_TAG_REPO_BOTH)), userId=userId)
        }
    }

    private val allModules = listOf(networkModule, repoModule, viewModelModule)

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(allModules)
        }
    }
}
