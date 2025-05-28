package com.mahmutgunduz.mealmate.data.repository

class YemekRetroRepository (var yds : YemeklerLocalDataSource) {

    suspend fun tumYemekleriGetir() : List<Yemekler> = yds.yemekleriYukle()









}