package com.example.cullina.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cullina.R
import com.example.cullina.models.Food
import com.example.cullina.models.Order

class HomeFragment : Fragment() {

    // Daftar 10 makanan - SEMUA akan ditampilkan
    private val foodList = listOf(
        Food(1, "Combro", "Combro asli Bandung adalah makanan dari tepung dan rasanya pedas", R.drawable.combro),
        Food(2, "Batagor", "Batagor Bandung adalah makanan dari tepung dan rasanya purih dengan bumbu kacang", R.drawable.batagor),
        Food(3, "Mie Kocok", "Mie kocok Bandung dengan kuah kaldu sapi", R.drawable.mie_kocok),
        Food(4, "Cireng", "Cireng crispy dengan bumbu rujak", R.drawable.cireng),
        Food(5, "Seblak", "Seblak kuah pedas dengan kerupuk dan makaroni", R.drawable.seblak),
        Food(6, "Karedok", "Karedok khas Sunda dengan bumbu kacang", R.drawable.karedok),
        Food(7, "Lotek", "Lotek dengan sayuran segar dan bumbu kacang", R.drawable.lotek),
        Food(8, "Colenak", "Colenak tape dengan kelapa parut", R.drawable.colenak),
        Food(9, "Surabi", "Surabi Bandung dengan topping gula merah", R.drawable.surabi),
        Food(10, "Es Cendol", "Es cendol dawet dengan gula merah", R.drawable.es_cendol)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val layoutFoods = view.findViewById<LinearLayout>(R.id.layoutFoods)

        // Set nama user dari SharedPreferences
        val tvHello = view.findViewById<TextView>(R.id.tvHello)
        val sharedPreferences = requireContext().getSharedPreferences("user_prefs", android.content.Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "User") ?: "User"
        tvHello.text = "Halo $username,"

        // Hapus semua view sebelumnya (jika ada)
        layoutFoods.removeAllViews()

        // Populate SEMUA 10 makanan
        foodList.forEach { food ->
            val foodView = inflater.inflate(R.layout.item_food, layoutFoods, false)

            val imageView = foodView.findViewById<ImageView>(R.id.ivFood)
            val tvName = foodView.findViewById<TextView>(R.id.tvFoodName)
            val tvDesc = foodView.findViewById<TextView>(R.id.tvFoodDesc)

            // Set data untuk setiap makanan
            imageView.setImageResource(food.imageRes)
            tvName.text = food.name
            tvDesc.text = food.description

            // Ketika item makanan di-klik, tambahkan ke pesanan
            foodView.setOnClickListener {
                Order.addFood(food)
                Toast.makeText(requireContext(), "${food.name} ditambahkan ke pesanan", Toast.LENGTH_SHORT).show()
            }

            layoutFoods.addView(foodView)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        // Refresh tampilan jika ada perubahan
        val layoutFoods = view?.findViewById<LinearLayout>(R.id.layoutFoods)
        if (layoutFoods != null) {
            // Jika perlu refresh data, bisa dilakukan di sini
        }
    }
}