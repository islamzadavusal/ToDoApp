package com.islamzada.todoapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.islamzada.todoapp.R
import com.islamzada.todoapp.adapter.MainAdapter
import com.islamzada.todoapp.databinding.FragmentMainBinding
import com.islamzada.todoapp.util.go
import com.islamzada.todoapp.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: MainAdapter

    val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {


        binding = FragmentMainBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // ProductListAdapter nesnesini oluşturun ve ayarlayın
        adapter = MainAdapter(requireContext(), mutableListOf(),


            onClick = { note ->
                val detailsFragment = DetailsFragment()

                val bundle = Bundle().apply {
                    putString("title", note.title)
                    putString("desc", note.desc)
                }

                detailsFragment.arguments = bundle

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host, detailsFragment)
                    .addToBackStack(null)
                    .commit()
            },

            // "onDeleteClick" fonksiyonu: Listedeki bir öğeyi sil
            onDeleteClick = { product ->
                viewModel.delete(product)
            }
        )

        binding.floatingButton.setOnClickListener {
            val transition = MainFragmentDirections.toSave()
            Navigation.go(it,transition)
        }

        binding.rvMain.adapter = adapter

        // ViewModel'den LiveData nesnesini alın ve nesneyi  obzerv etmek
        viewModel.getAllData().observe(viewLifecycleOwner, Observer { productList ->
            // Adaptöre yeni ürünler eklemek ve listeyi güncellemek
            adapter.addNewItem(productList)
        })

        return binding.root
    }
}
