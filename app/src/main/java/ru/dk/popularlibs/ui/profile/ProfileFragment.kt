package ru.dk.popularlibs.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import ru.dk.popularlibs.databinding.FragmentProfileBinding
import ru.dk.popularlibs.domain.GithubUser

class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = arguments?.getParcelable<GithubUser>("USER")
        if (user != null) renderData(user)

    }

    @SuppressLint("SetTextI18n")
    private fun renderData(githubUser: GithubUser) {
        with(binding) {
            ivUserAvatar.load(githubUser.avatarUrl)
            tvUserId.text = "ID: ${githubUser.id}"
            tvUserLogin.text = "Login: ${githubUser.login}"
        }
    }

    companion object {

        fun newInstance(args: Bundle) = ProfileFragment().apply {
            arguments = args
        }
    }
}