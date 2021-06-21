package com.edu.kotlindbnetwork.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edu.kotlindbnetwork.*
import com.edu.kotlindbnetwork.databinding.FragmentUserListBinding
import com.edu.kotlindbnetwork.data.db.user.User
import com.edu.kotlindbnetwork.repo.UserRepo
import com.edu.kotlindbnetwork.ui.adapters.UserAdapter
import com.edu.kotlindbnetwork.viewmodels.UserListViewModel
import javax.inject.Inject


class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding

    @Inject
    private lateinit var userRepo: UserRepo;


    private val model by lazy{
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return UserListViewModel(userRepo) as T
            }
        }).get(UserListViewModel::class.java)
    }

    private val adapter by lazy {
        UserAdapter({ singleClick(it) }, { model.getUsers() })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getComponent().injectsUserListFragment(this)
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
        binding.loadMoreButton.setOnClickListener({ model.getUsers() })
        binding.userProfileList.adapter = adapter
        binding.userProfileList.layoutManager = LinearLayoutManager(context)
        model.users.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    private fun singleClick(user: User){
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
        @JvmStatic
        fun newInstance() =
            UserListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}