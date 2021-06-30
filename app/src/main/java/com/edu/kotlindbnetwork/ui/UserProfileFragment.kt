package com.edu.kotlindbnetwork.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.edu.kotlindbnetwork.Consts
import com.edu.kotlindbnetwork.data.db.user.User
import com.edu.kotlindbnetwork.databinding.FragmentUserProfileBinding
import com.edu.kotlindbnetwork.viewmodels.UserProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class UserProfileFragment : Fragment() {
    private lateinit var binding: FragmentUserProfileBinding
    private val modelX by viewModel<UserProfileViewModel>(named(Consts.MODULE_VIEW_MODEL_USER_PROFILE)){
        parametersOf(arguments?.getString(Consts.FRAGMENT_USER_PROFILE_ARG_USER_ID) ?: "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachObservers()
    }

    private fun attachObservers(){
        modelX.user.observe(viewLifecycleOwner, { showUserContent(it) })
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