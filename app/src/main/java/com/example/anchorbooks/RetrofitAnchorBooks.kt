package com.example.anchorbooks

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAnchorBooks {
    companion object{
        private const val URL_BASE =" https://my-json-server.typicode.com/Himuravidal/anchorBooks/"


        fun retrofitInstance(): ApiBooks {
            val retrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return  retrofit.create(ApiBooks::class.java)

        }


    }
}