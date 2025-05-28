package com.mahmutgunduz.mealmate.di

import android.content.Context
import androidx.room.Room
import com.mahmutgunduz.mealmate.data.datasource.FavoriYemeklerDataSource
import com.mahmutgunduz.mealmate.data.datasource.SepetDataSource
import com.mahmutgunduz.mealmate.data.datasource.YemeklerLocalDataSource
import com.mahmutgunduz.mealmate.data.repository.FavoriYemeklerRepository
import com.mahmutgunduz.mealmate.data.repository.SepetRepository
import com.mahmutgunduz.mealmate.data.repository.YemekRetroRepository
import com.mahmutgunduz.mealmate.data.retrofit.ApiUtils
import com.mahmutgunduz.mealmate.data.retrofit.SepetlerDaoRetrofit
import com.mahmutgunduz.mealmate.data.retrofit.YemeklerDaoRetrofit
import com.mahmutgunduz.mealmate.room.AppDatabase
import com.mahmutgunduz.mealmate.room.FavoriYemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    //Yemekler
    fun provideYemeklerRepository(yds : YemeklerLocalDataSource) : YemekRetroRepository {
        return YemekRetroRepository(yds)
    }
    @Provides
    @Singleton
    fun provideYemeklerDataSource(ydao : YemeklerDaoRetrofit) : YemeklerLocalDataSource{
        return YemeklerLocalDataSource(ydao)
    }
    @Provides
    @Singleton
    fun provideYemeklerDao() : YemeklerDaoRetrofit{
        return ApiUtils.getYemeklerDao()
    }
    @Provides
    @Singleton
    //Sepet
    fun provideSepetRepository(sds : SepetDataSource) : SepetRepository {

        return SepetRepository(sds)
    }
    @Provides
    @Singleton
    fun provideSepetDataSource(sdao : SepetlerDaoRetrofit) : SepetDataSource {

        return SepetDataSource(sdao)
    }
    @Provides
    @Singleton
    fun provideSepetDao() : SepetlerDaoRetrofit {

        return ApiUtils.getSepetlerDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideFavoriDao(appDatabase: AppDatabase) : FavoriYemeklerDao {
        return appDatabase.favoriYemeklerDao()
    }

    @Provides
    @Singleton
    fun provideFavoriYemeklerDataSource(favoriDao : FavoriYemeklerDao) : FavoriYemeklerDataSource {

        return FavoriYemeklerDataSource(favoriDao)


    }

    @Provides
    @Singleton

    fun provideFavoriYemeklerRepository(favorids : FavoriYemeklerDataSource) : FavoriYemeklerRepository {
        return FavoriYemeklerRepository(favorids)
    }


















}