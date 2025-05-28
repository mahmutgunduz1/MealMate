package com.mahmutgunduz.mealmate.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.mahmutgunduz.mealmate.R
import com.mahmutgunduz.mealmate.data.entity.FavoriYemekler
import com.mahmutgunduz.mealmate.data.entity.SepetYemek
import com.mahmutgunduz.mealmate.data.entity.Yemekler
import com.mahmutgunduz.mealmate.databinding.FragmentDetailBinding
import com.mahmutgunduz.mealmate.ui.viewmodel.DetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailFragmentViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var currentYemek: Yemekler // SepetYemek değil, Yemekler tipi kullan daha iyi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.buttonDetayClose.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        val yemek: Yemekler? = args.yemek
        val sepet: SepetYemek? = args.sepet

        val yemekNesnesi = when {
            yemek != null -> yemek
            sepet != null -> viewModel.sepetYemekToYemekler(sepet)
            else -> null
        }

        yemekNesnesi?.let { yemekItem ->

            currentYemek = yemekItem // Atama yapıldı

            if (sepet != null) {
                // Sepette varsa sipariş adedini set et
                viewModel.setCurrentSepetYemek(sepet)
            } else {
                // Sepette yoksa miktar 1 ile başlasın
                viewModel.setInitialQuantity(1)
            }

            val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemekItem.yemek_resim_adi}"
            Glide.with(binding.imageDetail).load(imageUrl).override(300, 300).into(binding.imageDetail)

            binding.buttonDetayHeart.setOnClickListener {
                try {
                    val favori = FavoriYemekler(yemekItem.yemek_adi, yemekItem.yemek_resim_adi, yemekItem.yemek_id, yemekItem.yemek_fiyat)
                    viewModel.favoriyemekEkle(favori)
                    Snackbar.make(it, "${yemekItem.yemek_adi} favorilere eklendi", Snackbar.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                } catch (e: Exception) {
                    Log.e("MealsAdapter", "Favorilere ekleme hatası: ${e.message}")
                }
            }

            binding.textAddBacket.setOnClickListener { view ->
                viewModel.sepeteYemekEkle(yemekItem.yemek_adi, yemekItem.yemek_resim_adi, yemekItem.yemek_fiyat)
                Navigation.findNavController(view).popBackStack()
                Snackbar.make(view, "${yemekItem.yemek_adi} karta eklendi!", Snackbar.LENGTH_SHORT).show()
            }

            // quantity gözlemlemesi
            viewModel.quantity.observe(viewLifecycleOwner) { deger ->
                binding.buttonStock.text = deger.toString()

                val fiyat = yemekItem.yemek_fiyat.toDouble()
                val toplamFiyat = deger * fiyat

                binding.textPrice.text = String.format("₺ %.2f", toplamFiyat)
                binding.textView4.text = String.format("₺ %.2f", fiyat)

                binding.buttonMinus.alpha = if (deger <= 1) 0.1f else 1.0f
                binding.buttonMinus.isClickable = deger > 1
            }


            binding.buttonPlus.setOnClickListener {
                viewModel.increaseQuantity(currentYemek.yemek_adi, currentYemek.yemek_resim_adi, currentYemek.yemek_fiyat)
            }

            binding.buttonMinus.setOnClickListener {
                viewModel.decreaseQuantity(currentYemek.yemek_adi, currentYemek.yemek_resim_adi, currentYemek.yemek_fiyat)
            }
        }

        return binding.root
    }

}