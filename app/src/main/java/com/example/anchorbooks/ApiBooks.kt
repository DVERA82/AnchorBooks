package com.example.anchorbooks

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiBooks {
    @GET ("books")
    suspend fun fetchAnchorBooks(): Response<ResponseAnchorBooks>

    @GET ("bookDetail/{id}")
    suspend fun fetchBookDetail(@Path("id")id:Int): Response<ResponseBookDetail>
}