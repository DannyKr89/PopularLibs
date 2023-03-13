package ru.dk.popularlibs.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ru.dk.popularlibs.R
import ru.dk.popularlibs.databinding.ItemUsersBinding
import ru.dk.popularlibs.domain.GithubUser

class UsersViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
) {
    private val binding = ItemUsersBinding.bind(itemView)

    fun bind(userEntity: GithubUser, listener: ((GithubUser) -> Unit)?) {
        with(binding) {
            userItem.setOnClickListener {
                listener?.invoke(userEntity)
            }
            tvUserId.text = userEntity.id.toString()
            tvUserLogin.text = userEntity.login
            ivUserAvatar.load(userEntity.avatarUrl) {
                crossfade(true)
                transformations(RoundedCornersTransformation(16f))
            }
        }
    }
}