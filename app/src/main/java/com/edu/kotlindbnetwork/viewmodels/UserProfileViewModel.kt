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
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
    private val userRepo: UserRepo
): ViewModel() {
    private val liveUser: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = liveUser

    fun getUser(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepo.getUserById(userId)
            withContext(Dispatchers.Main) {
                liveUser.value = user
            }
        }
    }
    fun deleteUser(userId: String){
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.clearUserById(userId)
        }
    }

}