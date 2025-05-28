package com.mahmutgunduz.mealmate.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mahmutgunduz.mealmate.R
import com.mahmutgunduz.mealmate.databinding.FragmentMainBinding
import com.mahmutgunduz.mealmate.ui.adapter.MealsAdapter
import com.mahmutgunduz.mealmate.ui.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.anasayfaFragment = this

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filter(newText ?: "")
                return true
            }
        })

        viewModel.yemeklerListesi.observe(viewLifecycleOwner) { filteredYemekList ->
            val adapter = MealsAdapter(requireContext(), filteredYemekList, viewModel)
            binding.mealsadapter = adapter
            binding.recyclerView.adapter = adapter
        }



        return binding.root
    }
}