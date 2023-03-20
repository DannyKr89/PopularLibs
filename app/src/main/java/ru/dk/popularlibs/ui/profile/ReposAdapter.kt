package ru.dk.popularlibs.ui.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dk.popularlibs.R
import ru.dk.popularlibs.databinding.ItemReposBinding
import ru.dk.popularlibs.domain.GithubUserReposItem

class ReposAdapter : RecyclerView.Adapter<ReposAdapter.ReposViewHolder>() {

    private val data = mutableListOf<GithubUserReposItem>()

    var listener: ((GithubUserReposItem) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<GithubUserReposItem>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class ReposViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_repos, parent, false)
    ) {
        private val binding = ItemReposBinding.bind(itemView)

        fun bind(userReposItem: GithubUserReposItem) {
            with(binding) {
                tvReposName.text = userReposItem.name
                root.setOnClickListener {
                    listener?.invoke(userReposItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ReposViewHolder(parent)

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bind(data[position])
    }

}