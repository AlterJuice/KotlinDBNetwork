package com.edu.kotlindbnetwork.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edu.kotlindbnetwork.App
import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.R
import com.edu.kotlindbnetwork.databinding.FragmentUserProfileBinding
import com.edu.kotlindbnetwork.db.user.User
import com.edu.kotlindbnetwork.repo.UserRepo
import com.edu.kotlindbnetwork.viewmodels.UserProfileViewModel
import com.squareup.picasso.Picasso
import javax.inject.Inject


class UserProfileFragment : Fragment() {
    lateinit var binding: FragmentUserProfileBinding

    @Inject
    lateinit var userRepo: UserRepo

    private val model by lazy{
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return UserProfileViewModel(userRepo) as T
            }
        }).get(UserProfileViewModel::class.java)
    }


    private val userId by lazy {
        arguments?.getString(Consts.fragmentUserProfileArgUserId) ?: ""
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getComponent().injectsUserProfileFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model.user.observe(viewLifecycleOwner,{
            showUserContent(it)
        })
        model.getUser(userId)
    }

    private fun showUserContent(user: User){
        with(binding){
            userID.text = user.uid
            userName.text = "${user.firstName} ${user.lastName}"
            userEmail.text = user.email
            userPhone.text = user.phoneNumber
            loadImageIntoView(user.photoUrl, this.userPhoto, R.drawable.shape_circle)
        }
    }

    fun loadImageIntoView(imageUrl: String?, intoImageView: ImageView, placeholderResId: Int) {
        Picasso.get().load(imageUrl).noFade().placeholder(placeholderResId).into(intoImageView)
    }

    companion object {

        @JvmStatic
        fun newInstance(userId: String) =
            UserProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(Consts.fragmentUserProfileArgUserId, userId)
                }
            }
    }
}