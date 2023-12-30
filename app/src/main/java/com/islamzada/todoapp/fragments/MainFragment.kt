package com.islamzada.todoapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.islamzada.todoapp.R
import com.islamzada.todoapp.adapter.MainAdapter
import com.islamzada.todoapp.databinding.FragmentMainBinding
import com.islamzada.todoapp.entity.Favorite
import com.islamzada.todoapp.util.go
import com.islamzada.todoapp.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: MainAdapter

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel

        adapter = MainAdapter(
            requireContext(),
            mutableListOf(),
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
            onDeleteClick = { product ->
                mainViewModel.delete(product)
            },
            onFavIconClick = { note ->
                val favorite = Favorite(0, note.title, note.desc) // Assuming you have a constructor for Favorite class
                mainViewModel.insertToFav(favorite)
                Toast.makeText(requireContext(), "Note added to Favorite", Toast.LENGTH_SHORT)
                    .show()
            }
        )

        binding.floatingButton.setOnClickListener {
            val transition = MainFragmentDirections.toSave()
            Navigation.go(it, transition)
        }

        binding.rvMain.adapter = adapter

        mainViewModel.getAllData().observe(viewLifecycleOwner, Observer { productList ->
            adapter.addNewItem(productList)
        })

        binding.editTextSearchMain.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filterByName(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        return binding.root
    }
}
