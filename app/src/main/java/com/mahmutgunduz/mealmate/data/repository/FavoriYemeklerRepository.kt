package com.mahmutgunduz.mealmate.data.repository

import com.mahmutgunduz.mealmate.data.datasource.FavoriYemeklerDataSource
import com.mahmutgunduz.mealmate.data.entity.FavoriYemekler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriYemeklerRepository @Inject constructor(
    private val dataSource: FavoriYemeklerDataSource
) {

    suspend fun getAllFavoriYemekler(): List<FavoriYemekler> {
        return dataSource.getAllFavoriYemekler()
    }

    suspend fun favoriEkle(favoriYemek: FavoriYemekler) {
        dataSource.insertFavoriYemek(favoriYemek)
    }

    suspend fun favoriSil(favoriId: Int) {
        dataSource.deleteFavoriYemek(favoriId)
    }

    suspend fun isFavori(yemekAdi: String): Boolean {
        return dataSource.isFavori(yemekAdi)
    }
}