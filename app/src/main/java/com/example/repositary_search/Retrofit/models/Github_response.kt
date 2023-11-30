package com.example.repositary_search.Retrofit.models

data class Github_response(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)