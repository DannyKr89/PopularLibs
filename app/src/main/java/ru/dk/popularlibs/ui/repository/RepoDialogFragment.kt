package ru.dk.popularlibs.ui.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.dk.popularlibs.databinding.FragmentRepoDialogBinding
import ru.dk.popularlibs.domain.GithubUserReposItem

class RepoDialogFragment : DialogFragment() {

    private var _binding: FragmentRepoDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repo = extractRepository()
        initViews(repo)
    }

    private fun extractRepository(): GithubUserReposItem {
        val repository = arguments?.getParcelable<GithubUserReposItem>("REPO")
        return repository!!
    }

    private fun initViews(githubUserReposItem: GithubUserReposItem) {
        with(binding) {
            tvCreatedAt.text = githubUserReposItem.createdAt
            tvHtml.text = githubUserReposItem.htmlUrl
            tvDescription.text = githubUserReposItem.description
            tvName.text = githubUserReposItem.name
            tvDefaultBranch.text = githubUserReposItem.defaultBranch
            tvHomePage.text = githubUserReposItem.homepage
            tvLanguage.text = githubUserReposItem.language
            tvSize.text = githubUserReposItem.size.toString()
            tvUpdatedAt.text = githubUserReposItem.updatedAt
            tvVisibility.text = githubUserReposItem.visibility
            tvWatchers.text = githubUserReposItem.watchers.toString()
            tvForks.text = githubUserReposItem.forks.toString()
        }
    }

    companion object {

        fun newInstance(args: Bundle) = RepoDialogFragment().apply {
            arguments = args
        }
    }
}