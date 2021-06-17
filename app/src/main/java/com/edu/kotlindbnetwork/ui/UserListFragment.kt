package com.edu.kotlindbnetwork.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edu.kotlindbnetwork.DiUtil
import com.edu.kotlindbnetwork.R
import com.edu.kotlindbnetwork.repo.UserRepoDB
import com.edu.kotlindbnetwork.repo.UserRepoNetwork
import com.edu.kotlindbnetwork.viewmodels.UserListViewModel





class UserListFragment : Fragment() {

    lateinit var model: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = UserListViewModel(UserRepoNetwork(DiUtil.apiServiceInstance))
        model = UserListViewModel(UserRepoDB(DiUtil.databaseInstance))
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}