package com.mahmutgunduz.mealmate.data.retrofit

import retrofit2.Retrofit

class RetrofitClient {

    companion object{

        fun getClient(baseUrl : String) : Retrofit {

            return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}