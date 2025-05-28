package com.mahmutgunduz.mealmate.data.datasource

import com.mahmutgunduz.mealmate.data.entity.SepetYemek
import com.mahmutgunduz.mealmate.data.retrofit.SepetlerDaoRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.EOFException

class  SepetDataSource(private val sepetlerDao : SepetlerDaoRetrofit) {

    val kullaniciAdi = "mahmut"

    suspend fun sepettekiYemekleriGetir(): List<SepetYemek> =
        withContext(Dispatchers.IO) {
            try {
                val response = sepetlerDao.sepettekiYemekleriYukle(kullaniciAdi)
                response.sepetYemekler ?: emptyList()
            } catch (e: EOFException) {
                emptyList()
            } catch (e: Exception) {
                // Log ekle istersen, sonra boş liste dön
                emptyList()
            }
        }



    suspend fun sepeteEkle(yemek_adi : String,
                           yemek_resim_adi : String,
                           yemek_fiyat : Int,
                           yemek_siparis_adet : Int) {
        sepetlerDao.sepeteYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullaniciAdi)

    }

    suspend fun sepettenSil(sepet_yemek_id : Int) {

        sepetlerDao.sepettenYemekSil(sepet_yemek_id, kullaniciAdi)

    }
}