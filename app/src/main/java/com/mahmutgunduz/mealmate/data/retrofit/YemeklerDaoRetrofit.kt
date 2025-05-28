package com.mahmutgunduz.mealmate.data.retrofit

import com.mahmutgunduz.mealmate.data.entity.YemeklerCevap
import retrofit2.http.GET

interface YemeklerDaoRetrofit {

    @GET("tumYemekleriGetir.php")
    suspend fun tumYemekleriYukle(): YemeklerCevap

}