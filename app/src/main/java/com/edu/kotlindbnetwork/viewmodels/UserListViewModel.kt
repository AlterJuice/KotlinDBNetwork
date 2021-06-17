package com.edu.kotlindbnetwork.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.kotlindbnetwork.db.user.User
import com.edu.kotlindbnetwork.repo.UserRepoNetwork
import com.edu.kotlindbnetwork.response.UserResponse
import com.edu.kotlindbnetwork.response.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListViewModel(
    private val userRepo: UserRepoNetwork
) : ViewModel() {
    val liveUsers: MutableLiveData<List<User>> = MutableLiveData()

    fun getUsers(): LiveData<List<User>>{
        viewModelScope.launch {
            val users = userRepo.getUsers()
            withContext(Dispatchers.Main) {
                liveUsers.value = users
            }
        }
        return liveUsers
    }
}