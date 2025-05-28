package com.mahmutgunduz.mealmate.ui.adapter
import android.content.Context
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.google.android.material.snackbar.Snackbar
import com.mahmutgunduz.mealmate.data.entity.FavoriYemekler
import com.mahmutgunduz.mealmate.databinding.FavorilerAdapterBinding
import com.mahmutgunduz.mealmate.ui.viewmodel.FavorilerFragmentViewModel

class FavorilerAdapter(val mContext: Context,
                       var favoriliste : List<FavoriYemekler>,
                       val viewModel: FavorilerFragmentViewModel
) : RecyclerView.Adapter<FavorilerAdapter.FavoriAdapterTutucu>() {

    inner class FavoriAdapterTutucu(var binding : FavorilerAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriAdapterTutucu {
        val binding = FavorilerAdapterBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return FavoriAdapterTutucu(binding)
    }

    override fun onBindViewHolder(
        holder: FavoriAdapterTutucu,
        position: Int
    ) {

        var favoriler = favoriliste[position]





holder.binding.kart= favoriler







        t.imageDelete.setOnClickListener {
            Snackbar.make(it, "Silmek istediğinize emin misiniz ?", Snackbar.LENGTH_LONG).setAction("EVET") {
                viewModel.favoridenYemekSil(favoriler.yemek_id)
            }.show()
        }

        Glide.with(mContext).load("http://kasimadalan.pe.hu/yemekler/resimler/${favoriler.yemek_resim_adi}").override(300,300).into(t.textMealImage)





    }

    override fun getItemCount(): Int {
        return favoriliste.size
    }


}