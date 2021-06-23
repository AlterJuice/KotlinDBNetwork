package com.edu.kotlindbnetwork.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edu.kotlindbnetwork.App
import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.R
import com.edu.kotlindbnetwork.data.db.user.User
import com.edu.kotlindbnetwork.databinding.FragmentUserListBinding
import com.edu.kotlindbnetwork.ui.adapters.UserAdapter
import com.edu.kotlindbnetwork.viewmodels.UserListViewModel
import javax.inject.Inject


class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val model by lazy {
        ViewModelProvider(this, viewModelFactory).get(UserListViewModel::class.java)
    }

    private val adapter by lazy {
        UserAdapter({ onUserItemClick(it) }, { model.getUsers() })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userProfileList.adapter = adapter
        binding.userProfileList.layoutManager = LinearLayoutManager(context)
        model.users.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    private fun onUserItemClick(user: User) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.mainFragmentContainer, UserProfileFragment.newInstance(user.uid),
                Consts.FRAGMENT_USER_PROFILE_TAG
            )
            .addToBackStack(Consts.FRAGMENT_USER_PROFILE_TAG)
            .commit()
    }

    companion object {
        fun newInstance() = UserListFragment()
    }
}