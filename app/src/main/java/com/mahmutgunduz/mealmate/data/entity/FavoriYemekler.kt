package com.mahmutgunduz.mealmate.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favori_yemekler")
data class FavoriYemekler(
    @PrimaryKey(autoGenerate = false)
    val yemek_adi : String,
    val yemek_resim_adi : String,
    val yemek_fiyat : Int,
    val yemek_id : Int = 0)