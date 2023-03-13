package ru.dk.popularlibs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.dk.popularlibs.databinding.FragmentCounterBinding

class CounterFragment : MvpAppCompatFragment(), CountersView {

    private var _binding: FragmentCounterBinding? = null
    private val binding: FragmentCounterBinding get() = _binding!!
    private val presenter by moxyPresenter { Presenter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCounterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        with(binding) {
            btnFirstCounter.setOnClickListener { presenter.firstCounterClick() }
            btnSecondCounter.setOnClickListener { presenter.secondCounterClick() }
            btnThirdCounter.setOnClickListener { presenter.thirdCounterClick() }
        }
    }

    private fun initViews() {

        with(binding) {
            btnFirstCounter.text = presenter.getFirstCounter().toString()
            btnSecondCounter.text = presenter.getSecondCounter().toString()
            btnThirdCounter.text = presenter.getThirdCounter().toString()
        }
    }

    companion object {
        fun newInstance() =
            CounterFragment()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun setTextFirstCounter(text: String) {
        binding.btnFirstCounter.text = text
    }

    override fun setTextSecondCounter(text: String) {
        binding.btnSecondCounter.text = text
    }

    override fun setTextThirdCounter(text: String) {
        binding.btnThirdCounter.text = text
    }
}