package com.mahmutgunduz.mealmate.data.datasource

import com.mahmutgunduz.mealmate.data.entity.Yemekler

class YemeklerLocalDataSource(private val yemeklerDao: YemeklerDaoRetrofit) {

    suspend fun yemekleriYukle(): List<Yemekler> = withContext(Dispatchers.IO) {
        return@withContext yemeklerDao.tumYemekleriYukle().yemekler
    }

}