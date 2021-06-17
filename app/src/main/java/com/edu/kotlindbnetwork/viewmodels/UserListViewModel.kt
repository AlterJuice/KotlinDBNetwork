package com.edu.kotlindbnetwork.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.kotlindbnetwork.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListViewModel(
    private val userRepo: UserNetworkRepo
) : ViewModel() {

    fun getUsers(): LiveData<List<UserResponse.User>>{
        viewModelScope.launch {
            val users = userRepo.getUsers()
            withContext(Dispatchers.Main) {

            }
        }
    //        return MutableLiveData<List<UserResponse.User>>(Repo.getUsers().results)
    }
}