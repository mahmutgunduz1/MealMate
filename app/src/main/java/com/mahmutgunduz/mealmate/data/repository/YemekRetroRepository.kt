package com.mahmutgunduz.mealmate.data.repository

import com.mahmutgunduz.mealmate.data.datasource.YemeklerLocalDataSource
import com.mahmutgunduz.mealmate.data.entity.Yemekler

class YemekRetroRepository (var yds : YemeklerLocalDataSource) {

    suspend fun tumYemekleriGetir() : List<Yemekler> = yds.yemekleriYukle()









}