package com.edu.kotlindbnetwork.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.kotlindbnetwork.db.user.User
import com.edu.kotlindbnetwork.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListViewModel(
    private val userRepo: UserRepo
) : ViewModel() {
    private val liveUsers: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = liveUsers

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val users = userRepo.getUsers()
            withContext(Dispatchers.Main) {
                liveUsers.value = (liveUsers.value ?: listOf()) + users
            }
        }
    }
}