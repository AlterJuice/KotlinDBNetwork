package com.edu.kotlindbnetwork.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.kotlindbnetwork.data.db.user.User
import com.edu.kotlindbnetwork.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserProfileViewModel (
    private val userRepo: UserRepo,
    private val userId: String
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
}
