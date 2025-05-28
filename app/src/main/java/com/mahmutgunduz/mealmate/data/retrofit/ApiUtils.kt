package com.mahmutgunduz.mealmate.data.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/yemekler/"

        fun getYemeklerDao() : YemeklerDaoRetrofit {

            return RetrofitClient.getClient(BASE_URL).create(YemeklerDaoRetrofit::class.java)

        }

        fun getSepetlerDao() : SepetlerDaoRetrofit {

            return RetrofitClient.getClient(BASE_URL).create(SepetlerDaoRetrofit::class.java)
        }


    }
}