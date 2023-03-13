package ru.dk.popularlibs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.dk.popularlibs.databinding.FragmentCounterBinding

class CounterFragment : Fragment(), CountersContract.CountersView {

    private var _binding: FragmentCounterBinding? = null
    private val binding: FragmentCounterBinding get() = _binding!!
    private val presenter = Presenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCounterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)

        with(binding) {
            btnFirstCounter.setOnClickListener { presenter.counterClick(it, 0) }
            btnSecondCounter.setOnClickListener { presenter.counterClick(it, 1) }
            btnThirdCounter.setOnClickListener { presenter.counterClick(it, 2) }
        }
    }

    private fun initViews() {

        with(binding) {
            btnFirstCounter.text = presenter.model.getCounters()[0].toString()
            btnSecondCounter.text = presenter.model.getCounters()[1].toString()
            btnThirdCounter.text = presenter.model.getCounters()[2].toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray("counters", presenter.model.getCounters().toIntArray())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getIntArray("counters").let { array ->
            array?.let { it -> presenter.model.setCounters(it.toList()) }
        }
        initViews()
    }

    companion object {
        fun newInstance() =
            CounterFragment()
    }

    override fun onDestroyView() {
        _binding = null
        presenter.detach()
        super.onDestroyView()
    }

    override fun setText(view: View, text: String) {
        if (view is Button) {
            view.text = text
        }
    }
}