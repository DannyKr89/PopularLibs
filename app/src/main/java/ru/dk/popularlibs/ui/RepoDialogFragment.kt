package ru.dk.popularlibs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.dk.popularlibs.databinding.FragmentRepoDialogBinding

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
        val fork = arguments?.getString("FORK")
        binding.tvForks.text = fork
    }

    companion object {

        fun newInstance(args: Bundle) = RepoDialogFragment().apply {
            arguments = args
        }
    }
}