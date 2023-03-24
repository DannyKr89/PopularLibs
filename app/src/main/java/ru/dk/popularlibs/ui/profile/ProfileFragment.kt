package ru.dk.popularlibs.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.dk.popularlibs.App
import ru.dk.popularlibs.databinding.FragmentProfileBinding
import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.domain.GithubUserReposItem
import ru.dk.popularlibs.ui.repository.RepoDialogFragment

class ProfileFragment : MvpAppCompatFragment(), ReposView {


    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { ReposAdapter() }
    private val presenter by moxyPresenter { App.INSTANCE.reposPresenter }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = arguments?.getParcelable<GithubUser>("USER")
        if (user != null) renderData(user)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            rvReposList.layoutManager = LinearLayoutManager(requireContext())
            rvReposList.adapter = adapter
        }
    }

    @SuppressLint("SetTextI18n", "CheckResult")
    private fun renderData(githubUser: GithubUser) {
        with(binding) {
            ivUserAvatar.load(githubUser.avatarUrl)
            tvUserId.text = "ID: ${githubUser.id}"
            tvUserLogin.text = "Login: ${githubUser.login}"
        }
        presenter.loadData(githubUser)
    }

    companion object {

        fun newInstance(args: Bundle) = ProfileFragment().apply {
            arguments = args
        }
    }

    override fun showProgressbar(inProgress: Boolean) {
        //
    }

    override fun showRepos(reposItem: List<GithubUserReposItem>) {
        adapter.setData(reposItem)
        adapter.listener = {
            RepoDialogFragment.newInstance(Bundle().apply {
                putParcelable("REPO", it)
            }).show(childFragmentManager, it.name)
        }
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_SHORT).show()
    }
}