package com.mahmutgunduz.mealmate.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahmutgunduz.mealmate.data.entity.FavoriYemekler

@Database(entities = [FavoriYemekler::class], version = 1)
abstract  class AppDatabase : RoomDatabase(){

    abstract fun favoriYemeklerDao() : FavoriYemeklerDao
}