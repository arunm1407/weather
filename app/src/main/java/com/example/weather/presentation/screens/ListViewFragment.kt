package com.example.weather.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import com.example.weather.data.enitity.SearchResult
import com.example.weather.databinding.FragmentListViewBinding
import com.example.weather.presentation.DataResult
import com.example.weather.presentation.ListViewModel
import com.example.weather.presentation.adaptor.ListViewAdaptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn

/**
 * Created by Arun @ak - 14213  on 30/09/24.
 */
class ListViewFragment : Fragment(){


    private lateinit var binding : FragmentListViewBinding
    private val adaptor : ListViewAdaptor by lazy { ListViewAdaptor() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListViewBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        subscribeUI()
    }

    private fun subscribeUI() {
        binding.searchView.setQueryListener()
            .debounce(300)
            .filter { query ->
                query ?: return@filter
                 !(query.isEmpty() || query.length < 3)
            }
            .distinctUntilChanged()
            .flowOn(Dispatchers.Default)
            .flatMapLatest {query->

            }.collect { result->
                setupRecyclerViewData(result)
            }
    }

    private fun setupRecyclerViewData(result: DataResult<SearchResult>) {
        result.handle(
            onSuccess = {
                binding.progress.visibility = View.GONE
                binding.recyclerView.a
            },
            onLoading = {
                binding.progress.visibility = View.VISIBLE

            },
            onError = {

                // need to show place holder for the error
                binding.progress.visibility = View.GONE
            }
        )
    }

    private fun setupUI() {

   }

}

private fun SearchView.setQueryListener(): StateFlow<String?> {
    val data = MutableStateFlow<String?>("")
    setOnQueryTextListener(object : OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            data.value = newText
            return true
        }

    })
    return data
}