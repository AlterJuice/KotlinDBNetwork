package com.edu.kotlindbnetwork.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edu.kotlindbnetwork.*
import com.edu.kotlindbnetwork.databinding.FragmentUserListBinding
import com.edu.kotlindbnetwork.db.user.User
import com.edu.kotlindbnetwork.repo.UserRepo
import com.edu.kotlindbnetwork.repo.UserRepoDecorator
import com.edu.kotlindbnetwork.viewmodels.UserListViewModel
import com.squareup.picasso.Picasso
import dagger.internal.InjectedFieldSignature
import javax.inject.Inject


class UserListFragment : Fragment() {

    lateinit var binding: FragmentUserListBinding

    @Inject
    lateinit var userRepo: UserRepo;


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
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(context)
        model.users.observe(viewLifecycleOwner, {
            adapter.setItems(it)
        })
    }

    fun singleClick(user: User){
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment, UserProfileFragment.newInstance(user.uid),
                Consts.fragmentUserProfileTag
            )
            .addToBackStack(Consts.fragmentUserProfileTag)
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