package com.example.tutorm3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.view.isVisible

class KampusActivity : AppCompatActivity() {

    // Deklarasi semua komponen yang akan dipakai
    private lateinit var txtDeskripsi: TextView
    private lateinit var txtHilang: TextView
    private lateinit var btnKembali: Button
    private lateinit var rad1: RadioButton
    private lateinit var rad2: RadioButton

    val array_list = arrayOf(
        arrayOf("yes", "yes123"),
        arrayOf("no", "no123")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kampus)

        // Ambil semua komponen yang sudah dideklarasi dan simpan dalam variabel
        txtDeskripsi = findViewById(R.id.txtDeskripsi)
        txtHilang = findViewById(R.id.txtHilang)
        btnKembali = findViewById(R.id.btnKembali)
        rad1 = findViewById(R.id.rad1)
        rad2 = findViewById(R.id.rad2)

        // set default value
        txtDeskripsi.text = "Institut Sains dan Teknologi Terpadu Surabaya (ISTTS) adalah lembaga pendidikan tinggi swasta di Surabaya, Indonesia, yang didirikan pada tahun 1981. ISTTS menawarkan program sarjana dan diploma dalam bidang ilmu sains dan teknologi seperti Teknik Informatika, Teknik Elektro, Manajemen Informatika, dan Desain Komunikasi Visual."
        txtHilang.isVisible = true

        // Tambahkan event pada radiobutton
        rad1.setOnCheckedChangeListener{ buttonView, isChecked ->
            // buttonView merujuk pada radiobutton sendiri
            // panggil function buatan sendiri
            this.setDeskripsiValue(rad1, rad2)
            txtHilang.isVisible = false
        }
        rad2.setOnCheckedChangeListener{ buttonView, isChecked ->
            this.setDeskripsiValue(rad1, rad2)
            txtHilang.isVisible = true
        }
        btnKembali.setOnClickListener(){
            // IMPORTANT!
            // Untuk mengembalikan sebuah data ke intent pemanggil/asal, gunakan finish().
            // Jangan melakukan pemanggilan intent kembalian karena yang malah terjadi adalah intent
            // stacking yang semakin memakan resoure application.
            finish()
        }
    }

    fun setDeskripsiValue(rad1: RadioButton, rad2: RadioButton) {
        if (rad1.isChecked){
            txtDeskripsi.text = "Institut Sains dan Teknologi Terpadu Surabaya (ISTTS) adalah lembaga pendidikan tinggi swasta di Surabaya, Indonesia, yang didirikan pada tahun 1981. ISTTS menawarkan program sarjana dan diploma dalam bidang ilmu sains dan teknologi seperti Teknik Informatika, Teknik Elektro, Manajemen Informatika, dan Desain Komunikasi Visual."
            // ubah warna button
            // bisa custom warna dengan menambah warna pada folder res -> values -> colors.xml
            btnKembali.setBackgroundColor(resources.getColor(R.color.red))
            btnKembali.setText(array_list[0][0])
        }
        else{
            txtDeskripsi.text = "ISTTS aktif dalam menyelenggarakan kegiatan ekstrakurikuler, seminar, dan workshop untuk meningkatkan keterlibatan mahasiswa di luar kurikulum akademis. Dengan demikian, ISTTS menciptakan lingkungan yang mendukung pertumbuhan pribadi dan profesional mahasiswa serta mempersiapkan mereka untuk sukses di era teknologi yang cepat berkembang."
            btnKembali.setBackgroundColor(resources.getColor(R.color.teal))
            btnKembali.setText("Kembali")
        }
    }
}