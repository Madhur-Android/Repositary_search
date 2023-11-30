package com.example.repositary_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RatingBar
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repositary_search.Adapter.RepositoryAdapter
import com.example.repositary_search.Retrofit.models.Repository.NewRepository
import com.example.repositary_search.db.RepositoryDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var  viewModel: RepositoryViewModel
    lateinit var repositoryAdapter: RepositoryAdapter
    private lateinit var etSearch: EditText
    private lateinit var rvSearchNews: RecyclerView
    private lateinit var paginationProgressBar: ProgressBar
    val TAG = "searchRepository"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newRepository = NewRepository(RepositoryDatabase(this))
        val viewModelProviderFactory = RepositoryViewModelProviderFactory(newRepository)

        rvSearchNews = findViewById(R.id.rvSearchNews)
        paginationProgressBar = findViewById(R.id.paginationProgressBar)
        etSearch = findViewById(R.id.etSearch)

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(RepositoryViewModel::class.java)
        setUpRecyclerView()

        var job: Job?= null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if (editable.toString().isNotEmpty()){
                        viewModel.getRepository(editable.toString())
                    }
                }
            }
        }

        viewModel.searchRepository.observe(this, Observer { response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let { repositoryResponse ->
                        repositoryAdapter.differ.submitList(repositoryResponse.items)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "an error occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView(){
        repositoryAdapter = RepositoryAdapter()
        rvSearchNews.apply {
            adapter = repositoryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)

        }
    }
}