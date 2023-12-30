package com.islamzada.todoapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.islamzada.todoapp.R
import com.islamzada.todoapp.adapter.FavAdapter
import com.islamzada.todoapp.adapter.MainAdapter
import com.islamzada.todoapp.databinding.FragmentFavouriteBinding
import com.islamzada.todoapp.databinding.FragmentMainBinding
import com.islamzada.todoapp.entity.Favorite
import com.islamzada.todoapp.util.go
import com.islamzada.todoapp.viewModels.FavoriteViewModel
import com.islamzada.todoapp.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var adapter: FavAdapter

    private val favViewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {


        binding = FragmentFavouriteBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = favViewModel

        // ProductListAdapter nesnesini oluşturun ve ayarlayın
        adapter = FavAdapter(requireContext(), mutableListOf(),


            onClick = { fav ->
                val detailsFragment = DetailsFragment()

                val bundle = Bundle().apply {
                    putString("title", fav.title)
                    putString("desc", fav.desc)
                }

                detailsFragment.arguments = bundle

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host, detailsFragment)
                    .addToBackStack(null)
                    .commit()
            },

            // "onDeleteClick" fonksiyonu: Listedeki bir öğeyi sil
            onDeleteClick = { fav ->
                favViewModel.deleteFav(fav)
            }
        )

        binding.rvFav.adapter = adapter

        // ViewModel'den LiveData nesnesini alın ve nesneyi  obzerv etmek
        favViewModel.getAllDataFav().observe(viewLifecycleOwner, Observer { productList ->
            // Adaptöre yeni ürünler eklemek ve listeyi güncellemek
            adapter.addNewItem(productList)
        })

        binding.editTextSearchFav.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filterByName(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        return binding.root
    }
}
