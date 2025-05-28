package com.mahmutgunduz.mealmate.data.datasource

import com.mahmutgunduz.mealmate.data.entity.Yemekler
import com.mahmutgunduz.mealmate.data.retrofit.YemeklerDaoRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerLocalDataSource(private val yemeklerDao: YemeklerDaoRetrofit) {

    suspend fun yemekleriYukle(): List<Yemekler> = withContext(Dispatchers.IO) {
        return@withContext yemeklerDao.tumYemekleriYukle().yemekler
    }

}