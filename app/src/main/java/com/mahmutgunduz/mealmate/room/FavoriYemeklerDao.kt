package com.mahmutgunduz.mealmate.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mahmutgunduz.mealmate.data.entity.FavoriYemekler

@Dao
interface FavoriYemeklerDao {

    @Query("SELECT * FROM favori_yemekler")
    suspend fun getAllFavoriYemekler() : List<FavoriYemekler>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriYemek(favoriyemek : FavoriYemekler)

    @Query("DELETE FROM favori_yemekler WHERE yemek_id = :yemekId")
    suspend fun deleteFavoriYemek(yemekId : Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favori_yemekler WHERE yemek_adi = :yemekAdi)")
    suspend fun isFavori(yemekAdi: String): Boolean
}