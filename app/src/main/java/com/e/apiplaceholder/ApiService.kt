package com.e.apiplaceholder

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(value="posts")
    suspend fun getComentarios():Response<List<Comments>>
}