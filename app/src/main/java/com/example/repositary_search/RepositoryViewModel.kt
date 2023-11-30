package com.example.repositary_search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repositary_search.Retrofit.models.Github_response
import com.example.repositary_search.Retrofit.models.Repository.NewRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class RepositoryViewModel(val Repository: NewRepository): ViewModel() {

    val searchRepository: MutableLiveData<Resource<Github_response>> = MutableLiveData()
    var searchRepositoryPage = 1

    init {
        getRepository("android")
    }

    fun getRepository(q: String) = viewModelScope.launch {
        searchRepository.postValue(Resource.Loading())
        val response = Repository.getSearchRepository(q, searchRepositoryPage)
        searchRepository.postValue(handleSearchRepositoryResponse(response))
    }
    private fun handleSearchRepositoryResponse(response: Response<Github_response>): Resource<Github_response>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}