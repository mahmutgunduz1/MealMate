package com.mahmutgunduz.mealmate.data.repository

class SepetRepository(var sds : SepetDataSource) {


    suspend fun sepettekiYemekleriGetir(): List<SepetYemek> = sds.sepettekiYemekleriGetir()

    suspend fun sepeteYemekEkle(yemek_adi : String,
                                yemek_resim_adi : String,
                                yemek_fiyat : Int,
                                yemek_siparis_adet : Int) {
        sds.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet)

    }

    suspend fun sepetYemekSil(sepet_yemek_id : Int) {
        sds.sepettenSil(sepet_yemek_id)
    }



}