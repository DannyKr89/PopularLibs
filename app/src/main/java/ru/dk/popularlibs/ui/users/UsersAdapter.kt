package ru.dk.popularlibs.ui.users

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ru.dk.popularlibs.R
import ru.dk.popularlibs.databinding.ItemUsersBinding
import ru.dk.popularlibs.domain.GithubUser

class UsersAdapter() : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
    private val data = mutableListOf<GithubUser>()
    var listener: ((GithubUser) -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    inner class UsersViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
    ) {
        private val binding = ItemUsersBinding.bind(itemView)

        fun bind(userEntity: GithubUser) {
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

    override fun getItemId(position: Int): Long {
        return data[position].id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UsersViewHolder(parent)

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(data[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<GithubUser>) {
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }
}
