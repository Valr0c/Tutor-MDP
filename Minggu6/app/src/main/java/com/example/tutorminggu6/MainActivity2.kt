package com.example.tutorminggu6

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.tutorminggu6.databinding.ActivityMain2Binding
import com.google.android.material.color.MaterialColors

class MainActivity2 : AppCompatActivity() {
    private val vm:ProfileViewModel by viewModels()
    private lateinit var binding:ActivityMain2Binding

    /**
     * Kita akan mencoba menghubungkan ViewModel langsung dengan layout XML menggunakan Data Binding
     * tambahkan sebuah variabel untuk vm pada layout
     *
     *      <variable name="vm"  type="com.example.tutorminggu6.ProfileViewModel" />
     *
     * lalu sambungkan vm pada layout binding
     * Agar ViewModel bekerja dengan baik, tambahkan satu code untuk setting lifecycleOwner
     *
     *      binding.lifecycleOwner = this
     *
     * hal ini dilakukan untuk memberitahu layout, siapa yang menjadi konteks lifecylce Vm tersebut
     *
     * Dengan tersambungnya VM, kita bisa menghubungkan setiap variabel pada ViewModel secara langsung
     * untuk memanggilnya kita cukup akses dengan contoh berikut:
     *
     *      android:text="@{vm.suatuVariabel}"
     *      android:onClick="@{() -> vm.suatuMethod()}"  // untuk method
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        binding.vm = vm
        binding.lifecycleOwner = this

        /**
         * Selain menempel data langsung ke layout,
         * kita juga bisa menggunakan observer untuk mengupdate layout sesuai data
         *
         * Untuk membuat Observer, buatlah sebuah object Observer dengan tipe data yang diinginkan
         * Dalam observer tersebut buatlah callback yang akan dijalankan pada saat terjadi update
         *
         * Setelah itu gunakan fungsi observe pada variabel viewmodel bertipe LiveData
         * Di dalam variabel observe tersebut isilah owner activity dan observer tersebut
         */
        val pointObserver = Observer<Int>{
            binding.tvPoint.text = "Point: ${it}"
        }
        vm.poin.observe(this, pointObserver)


        binding.btBack.setOnClickListener {
            finish()
        }
        binding.btNext.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }


        // sebuah cara untuk memilih button dan memindahkan pilihan
        var selected_class = binding.btNoClass.id
        val class_buttons:MutableList<Button> = mutableListOf(
            binding.btWarrior,
            binding.btMage,
            binding.btPaladin,
            binding.btNoClass)

        fun updateClass(id:Int){
            selected_class = id
            when(selected_class){
                binding.btWarrior.id -> {
                    vm.changeClass("WARRIOR")
                }
                binding.btMage.id -> {
                    vm.changeClass("MAGE")
                }
                binding.btPaladin.id -> {
                    vm.changeClass("PALADIN")
                }
                binding.btNoClass.id -> {
                    vm.changeClass("NOCLASS")
                }
            }
            for (bt in class_buttons){
                /**
                 * Kalau mau merubah image pada ImageButton
                 * binding.imageButtonKu.setImageResource(R.drawable.nama_gambar)
                 */
                bt.setBackgroundColor(
                    if (bt.id == selected_class) Color.RED else Color.BLUE
                )
            }
        }

        for (bt in class_buttons){
            bt.setBackgroundColor(
                if (bt.id == selected_class) Color.RED else Color.BLUE
            )
            bt.setOnClickListener {
                updateClass(bt.id)
            }
        }
    }
}