package com.pri.airquality.ui.search

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.pri.airquality.R
import com.pri.airquality.adapter.SearchedCityAdapter
import com.pri.airquality.databinding.FragmentSearchBinding
import com.pri.airquality.data.model.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var binding: FragmentSearchBinding
    private var alertDialog: AlertDialog? = null
    private lateinit var adapter: SearchedCityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSearchBinding
            .inflate(inflater, container, false)
            .apply { binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchedCityAdapter()
        binding.rvCity.adapter = adapter
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.callAirQualityApi(city = query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchCity(query = newText.orEmpty())
                return true
            }

        })
        viewModel.stationResponse.observe(viewLifecycleOwner) {
            if (!viewModel.apiObserved) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            viewModel.apiObserved = true
                            showProgress(false)
                            resource.data?.let { stations ->
                                viewModel.insertStations(stations)
                                viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                                    delay(100)
                                    adapter.submitList(stations)
                                }

                            } ?: kotlin.run {
                                resource.message?.let { message -> showError(message) }
                            }
                        }
                        Status.ERROR -> {
                            viewModel.apiObserved = true
                            showProgress(false)
                            resource.message?.let { message -> showError(message) }
                        }
                        Status.LOADING -> showProgress(true)
                    }

                }
            }

        }
        viewModel.searchResult.observe(viewLifecycleOwner) { suggestions ->
            adapter.submitList(suggestions)
        }
    }

    private fun showProgress(show: Boolean) {
        binding.progressBar.isVisible = show
    }

    private fun showError(message: String) {
        alertDialog?.dismiss()
        alertDialog = AlertDialog
            .Builder(requireContext())
            .setTitle(getString(R.string.error))
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        alertDialog?.dismiss()
    }

}