package com.example.joblist.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.joblist.R

abstract class BaseFragment : Fragment() {
    private lateinit var svSearch: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_options, menu)
        svSearch = menu.findItem(R.id.itSearch).actionView as SearchView
        svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                svSearch.clearFocus()
                onSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                onSearch(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itLogout -> {
                activity?.finish()
            }
            R.id.itCreateJob -> {
                onCreateJob()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    open fun onSearch(query: String?) {}

    open fun onCreateJob() {}

    fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
