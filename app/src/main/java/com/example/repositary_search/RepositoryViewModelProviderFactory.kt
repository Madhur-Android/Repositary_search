package com.example.repositary_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.repositary_search.Retrofit.models.Repository.NewRepository

class RepositoryViewModelProviderFactory(
    val newRepository: NewRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
         return RepositoryViewModel(newRepository) as T
    }
}