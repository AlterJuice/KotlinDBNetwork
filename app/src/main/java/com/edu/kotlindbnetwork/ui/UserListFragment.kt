package com.edu.kotlindbnetwork.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.data.db.user.User
import com.edu.kotlindbnetwork.databinding.FragmentUserListBinding
import com.edu.kotlindbnetwork.ui.adapters.UserAdapter
import com.edu.kotlindbnetwork.viewmodels.UserListViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.qualifier.named

class UserListFragment : Fragment() {
    private lateinit var binding: FragmentUserListBinding
    private val viewModel by sharedViewModel<UserListViewModel>(named(Consts.MODULE_VIEW_MODEL_USER_LIST))

    private val adapter by lazy {
        UserAdapter({ onUserItemClick(it) }, { viewModel.getUsers() })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun attachObservers(){
        viewModel.users.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }
    private fun configureUIList(){
        binding.userProfileList.adapter = adapter
        binding.userProfileList.layoutManager = LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureUIList()
        attachObservers()
    }

    private fun onUserItemClick(user: User) {
        loadUserProfileFragment(user)
    }

    private fun loadUserProfileFragment(user: User){
        with(requireActivity()){
            val fragment = UserProfileFragment.newInstance(user.uid)
            if (this is MainActivity) {
                replaceFragment(fragment, Consts.FRAGMENT_USER_PROFILE_TAG)
            }
//            }else{
//                throw UnsupportedOperationException("Supports MainActivity, got ${this.javaClass}")
//            }
        }
    }

    companion object {
        fun newInstance() = UserListFragment()
    }
}