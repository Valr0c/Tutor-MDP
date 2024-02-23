package com.example.tutorm3

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.tutorm3.databinding.ActivityKampusBinding

class KampusActivity : AppCompatActivity() {

    // Deklarasi semua komponen yang akan dipakai
    private lateinit var txtDeskripsi: TextView
    private lateinit var txtHilang: TextView
    private lateinit var btnKembali: Button
    private lateinit var btnSMS: Button
    private lateinit var rad1: RadioButton
    private lateinit var rad2: RadioButton

    // View binding ini ngebind semua komponen view dalam satu variable kotlin.
    // Dengan view binding kita gk harus findViewById()... sebanyak view yang mau kita bind
    // Cukup pakai 1 line saja dan semua komponen view bisa diakses
    // Untuk menggunakan view binding, tambahkan line berikut pada build.gradle (Module :app) dalam scope android{}
    // android {
    //     ...
    //     buildFeatures {
    //         viewBinding = true
    //     }
    // }

    //Deklarasi variabel dengan tipe berformat <NamaActivity>Binding
    private lateinit var binding: ActivityKampusBinding

    val array_list = arrayOf(
        arrayOf("yes", "yes123"),
        arrayOf("no", "no123")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kampus)

        // ==========================================================
        binding = ActivityKampusBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        /*
        Ketiga line di atas harus ditulis dalam sebuah onCreate activity SETELAH super.onCreate()
        Cara ngakses variabel viewnya dengan binding.<nama view>
        Misal: binding.etUsername.text = "hi"
         */

        // Ambil semua komponen yang sudah dideklarasi dan simpan dalam variabel
        // txtDeskripsi = findViewById(R.id.txtDeskripsi)
        // txtHilang = findViewById(R.id.txtHilang)
        // btnKembali = findViewById(R.id.btnKembali)
        // rad1 = findViewById(R.id.rad1)
        // rad2 = findViewById(R.id.rad2)

        // set default value
        binding.txtDeskripsi.text = "Institut Sains dan Teknologi Terpadu Surabaya (ISTTS) adalah lembaga pendidikan tinggi swasta di Surabaya, Indonesia, yang didirikan pada tahun 1981. ISTTS menawarkan program sarjana dan diploma dalam bidang ilmu sains dan teknologi seperti Teknik Informatika, Teknik Elektro, Manajemen Informatika, dan Desain Komunikasi Visual."
        binding.txtHilang.isVisible = true

        // Tambahkan event pada radiobutton
        binding.rad1.setOnCheckedChangeListener{ buttonView, isChecked ->
            // buttonView merujuk pada radiobutton sendiri
            // panggil function buatan sendiri
            this.setDeskripsiValue(binding.rad1, binding.rad2)
            binding.txtHilang.isVisible = false
        }
        binding.rad2.setOnCheckedChangeListener{ buttonView, isChecked ->
            this.setDeskripsiValue(binding.rad1, binding.rad2)
            binding.txtHilang.isVisible = true
        }
        binding.btnKembali.setOnClickListener(){
            // IMPORTANT!
            // Untuk mengembalikan sebuah data ke intent pemanggil/asal, gunakan finish().
            // Jangan melakukan pemanggilan intent kembalian karena yang malah terjadi adalah intent
            // stacking yang semakin memakan resoure application.
            finish()
        }
        // SMS
        binding.btnSMS.setOnClickListener(){
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:081234567890")
                putExtra("sms_body", "Halo, mama minta pulsa ya nak, lagi tersesat di tengah hutan, pulsa 500 ribu aja... Makasih ya..")
            }
            startActivity(intent)
        }
    }

    fun setDeskripsiValue(rad1: RadioButton, rad2: RadioButton) {
        if (rad1.isChecked){
            binding.txtDeskripsi.text = "Institut Sains dan Teknologi Terpadu Surabaya (ISTTS) adalah lembaga pendidikan tinggi swasta di Surabaya, Indonesia, yang didirikan pada tahun 1981. ISTTS menawarkan program sarjana dan diploma dalam bidang ilmu sains dan teknologi seperti Teknik Informatika, Teknik Elektro, Manajemen Informatika, dan Desain Komunikasi Visual."
            // ubah warna button
            // bisa custom warna dengan menambah warna pada folder res -> values -> colors.xml
            binding.btnKembali.setBackgroundColor(resources.getColor(R.color.red))
            binding.btnKembali.setText(array_list[0][0])
        }
        else{
            binding.txtDeskripsi.text = "ISTTS aktif dalam menyelenggarakan kegiatan ekstrakurikuler, seminar, dan workshop untuk meningkatkan keterlibatan mahasiswa di luar kurikulum akademis. Dengan demikian, ISTTS menciptakan lingkungan yang mendukung pertumbuhan pribadi dan profesional mahasiswa serta mempersiapkan mereka untuk sukses di era teknologi yang cepat berkembang."
            binding.btnKembali.setBackgroundColor(resources.getColor(R.color.teal))
            binding.btnKembali.setText("Kembali")
        }
    }
}