package com.edu.kotlindbnetwork.viewmodels

import androidx.lifecycle.*
import com.edu.kotlindbnetwork.data.db.user.User
import com.edu.kotlindbnetwork.repo.UserRepo
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserProfileViewModel @AssistedInject constructor(
    private val userRepo: UserRepo,
    @Assisted private val userId: String
): ViewModel() {

    private val liveUser: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = liveUser

    init {
        getUser(userId)
    }

    private fun getUser(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepo.getUserById(userId)
            withContext(Dispatchers.Main) {
                liveUser.value = user
            }
        }
    }


    companion object {
        fun provideFactory(
            factory: Factory,
            userId: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return factory.create(userId) as T
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(userId: String): UserProfileViewModel
    }
}
