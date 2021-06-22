package com.edu.kotlindbnetwork.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.edu.kotlindbnetwork.data.db.user.User
import com.edu.kotlindbnetwork.databinding.UserListItemBinding


class UserAdapter(
    private val clickListener: (User) -> Unit,
    private val onPageEndReached: () -> Unit
) : ListAdapter<User, UserAdapter.ViewHolder>(UserDifferenceCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UserListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (itemCount - position == 3) {
            onPageEndReached()
        }
        holder.bind(getItem(position), clickListener)
    }

    inner class ViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, onItemClick: (User) -> Unit) {
            binding.root.setOnClickListener {
                onItemClick(user)
            }
            binding.root.onFocusChangeListener = object : View.OnFocusChangeListener {
                override fun onFocusChange(v: View?, hasFocus: Boolean) {
                    if (v == null) return
                    var scale = 1f
                    if (hasFocus) scale += 0.1f
                    v.animate().scaleX(scale).scaleY(scale).setDuration(300).start()
                }
            }
            binding.itemID.text = user.firstName
        }
    }


    class UserDifferenceCallback : DiffUtil.ItemCallback<User>() {
        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.firstName == newItem.firstName
                    && oldItem.lastName == newItem.lastName
                    && oldItem.email == newItem.email
                    && oldItem.phoneNumber == newItem.phoneNumber
                    && oldItem.uid == newItem.uid
                    && oldItem.photoUrl == newItem.photoUrl
        }

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.uid == newItem.uid
        }
    }


}