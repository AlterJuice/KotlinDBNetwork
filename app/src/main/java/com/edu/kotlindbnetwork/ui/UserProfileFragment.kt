package com.edu.kotlindbnetwork.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.edu.kotlindbnetwork.App
import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.data.db.user.User
import com.edu.kotlindbnetwork.databinding.FragmentUserProfileBinding
import com.edu.kotlindbnetwork.viewmodels.UserProfileViewModel
import javax.inject.Inject


class UserProfileFragment : Fragment() {
    private lateinit var binding: FragmentUserProfileBinding

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val model by lazy {
        ViewModelProvider(this, viewModelFactory).get(UserProfileViewModel::class.java)
    }

    private val userId by lazy {
        arguments?.getString(Consts.FRAGMENT_USER_PROFILE_ARG_USER_ID) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model.user.observe(viewLifecycleOwner, {
            showUserContent(it)
        })
        model.getUser(userId)
    }

    private fun showUserContent(user: User) {
        with(binding) {
            val firstLastName = "${user.firstName} ${user.lastName}"
            userID.text = user.uid
            userName.text = firstLastName
            userEmail.text = user.email
            userPhone.text = user.phoneNumber
            loadImageIntoView(user.photoUrl, this.userPhoto)
            (requireActivity() as MainActivity).setBarSubtitle(firstLastName)
        }
    }

    private fun loadImageIntoView(imageUrl: String?, intoImageView: ImageView) {
        Glide.with(intoImageView.context).load(imageUrl).circleCrop().into(intoImageView)
    }

    companion object {
        fun newInstance(userId: String) =
            UserProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(Consts.FRAGMENT_USER_PROFILE_ARG_USER_ID, userId)
                }
            }
    }
}