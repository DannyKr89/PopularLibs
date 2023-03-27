package ru.dk.popularlibs.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.dk.popularlibs.App
import ru.dk.popularlibs.databinding.FragmentUsersBinding
import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.ui.cicerone.BackButtonListener

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { UsersAdapter() }
    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter().apply {
            App.INSTANCE.initUsersSubcomponent().inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        with(binding) {
            rvUsersList.layoutManager = LinearLayoutManager(requireContext())
            rvUsersList.adapter = adapter
            btnRefresh.setOnClickListener {
                presenter.loadData()
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        App.INSTANCE.endUserScope()
        super.onDestroy()
    }

    override fun showProgressbar(inProgress: Boolean) {
        with(binding) {
            rvUsersList.isVisible = !inProgress
            progress.isVisible = inProgress
            btnRefresh.isEnabled = !inProgress
        }
    }

    override fun showUsers(users: List<GithubUser>) {
        adapter.setData(users)
        adapter.listener = {
            presenter.navigateToProfile(it)
        }
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun backPressed() = presenter.backPressed()
}