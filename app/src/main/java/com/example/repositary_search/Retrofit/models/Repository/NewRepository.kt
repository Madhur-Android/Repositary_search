package com.example.repositary_search.Retrofit.models.Repository

import com.example.repositary_search.Retrofit.Api.RetrofitInstance
import com.example.repositary_search.db.RepositoryDatabase

class NewRepository(val db: RepositoryDatabase) {

    suspend fun getSearchRepository(q: String, page: Int) =
        RetrofitInstance.Api.getRepository(q, page)


}