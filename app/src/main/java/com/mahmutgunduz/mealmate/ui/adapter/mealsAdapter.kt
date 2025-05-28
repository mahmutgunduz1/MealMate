package com.mahmutgunduz.mealmate.ui.adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.google.android.material.snackbar.Snackbar
import com.mahmutgunduz.mealmate.R
import com.mahmutgunduz.mealmate.data.entity.FavoriYemekler
import com.mahmutgunduz.mealmate.data.entity.Yemekler
import com.mahmutgunduz.mealmate.databinding.HolderAdapterBinding
import com.mahmutgunduz.mealmate.ui.fragment.MainFragmentDirections
import com.mahmutgunduz.mealmate.ui.viewmodel.MainFragmentViewModel
import com.mahmutgunduz.mealmate.utils.gecisYap


class MealsAdapter(
    private val mContext: Context,
    private val yemekler: List<Yemekler>,
    private val viewModel: MainFragmentViewModel
) : RecyclerView.Adapter<MealsAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(val binding: HolderAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding: HolderAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.holder_adapter, parent, false
        )
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemekler[position]
        val t = holder.binding


        t.yemek = yemek



        t.cardViewYemek.setOnClickListener {
            try {
                val action = MainFragmentDirections.mainToDetail(yemek = yemek, sepet = null)
                Navigation.gecisYap(it, action)
            } catch (e: Exception) {
                Log.e("MealsAdapter", "Navigation error: ${e.message}")
            }
        }
        t.buttonHeartt.setOnClickListener {
            try {
                val favori = FavoriYemekler(yemek_adi = yemek.yemek_adi, yemek_resim_adi = yemek.yemek_resim_adi, yemek_id = yemek.yemek_id, yemek_fiyat = yemek.yemek_fiyat)
                viewModel.favoriyemekEkle(favori)
                Snackbar.make(it, "${yemek.yemek_adi} favorilere eklendi", Snackbar.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e("MealsAdapter", "Favorilere ekleme hatası: ${e.message}")
            }
        }




        t.fabAddToCart.setOnClickListener {
            try {
                viewModel.sepeteYemekEkle(yemek.yemek_adi, yemek.yemek_resim_adi, yemek.yemek_fiyat)
                Snackbar.make(it, "${yemek.yemek_adi} sepete eklendi", Snackbar.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e("MealsAdapter", "Sepete ekleme hatası: ${e.message}")
            }
        }



        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(mContext)
            .load(url)
            .override(300, 300)
            .into(t.imageView)
    }

    override fun getItemCount(): Int = yemekler.size
}