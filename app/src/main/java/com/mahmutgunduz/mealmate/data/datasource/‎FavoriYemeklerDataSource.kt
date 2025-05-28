package com.mahmutgunduz.mealmate.data.datasource

import com.mahmutgunduz.mealmate.data.entity.FavoriYemekler
import com.mahmutgunduz.mealmate.room.FavoriYemeklerDao

class FavoriYemeklerDataSource(private val dao: FavoriYemeklerDao) {

    suspend fun getAllFavoriYemekler(): List<FavoriYemekler> = dao.getAllFavoriYemekler()

    suspend fun insertFavoriYemek(favoriYemek: FavoriYemekler) = dao.insertFavoriYemek(favoriYemek)

    suspend fun deleteFavoriYemek(favoriId : Int) = dao.deleteFavoriYemek(favoriId)

    suspend fun isFavori(yemekAdi: String) = dao.isFavori(yemekAdi)
}