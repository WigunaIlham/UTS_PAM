package com.example.cullina

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cullina.models.Order

class AddressActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        val etNamaLengkap = findViewById<EditText>(R.id.etNamaLengkap)
        val etAlamat = findViewById<EditText>(R.id.etAlamat)
        val etPatokan = findViewById<EditText>(R.id.etPatokan)
        val btnOrder = findViewById<Button>(R.id.btnOrder)

        val fullName = sharedPreferences.getString("full_name", "")
        etNamaLengkap.setText(fullName)

        btnOrder.setOnClickListener {
            val namaLengkap = etNamaLengkap.text.toString()
            val alamat = etAlamat.text.toString()
            val patokan = etPatokan.text.toString()

            if (namaLengkap.isNotEmpty() && alamat.isNotEmpty() && patokan.isNotEmpty()) {
                val intent = Intent(this, OrderSuccessActivity::class.java)
                startActivity(intent)
                Order.clearOrder()
                finish()
            } else {
                Toast.makeText(this, "Harap isi semua field alamat", Toast.LENGTH_SHORT).show()
            }
        }
    }
}