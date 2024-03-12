package com.example.basictutorm4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class AddMhsActivity : AppCompatActivity() {
    var mode:String? = "INSERT"
    lateinit var buttonAddMahasiswa: Button
    lateinit var etNRP: EditText
    lateinit var etNama: EditText
    lateinit var spinnerJurusan: Spinner

    var listJurusan:ArrayList<String> = arrayListOf("Informatika", "SIB", "DKV", "Elektro",
        "Lainnya")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_mhs)
        buttonAddMahasiswa = findViewById(R.id.buttonAddMahasiswa)
        etNRP = findViewById(R.id.etNRP)
        etNama = findViewById(R.id.etNama)
        spinnerJurusan = findViewById(R.id.spinnerJurusan)

        mode = intent.getStringExtra("mode")

        //untuk isi data spinner bisa dua cara, static atau dynamic
        //untuk static, isi langsung di property entries pada component
        //untuk dynamic, perlu dideclare dulu array

          var spinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
              this,android.R.layout.simple_spinner_item,listJurusan)
          spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
          spinnerJurusan.adapter = spinnerAdapter


        if(mode=="UPDATE"){
            //isi sesuai data yang dipilih
            buttonAddMahasiswa.text = "Edit"
            var nrp: String? = intent.getStringExtra("nrp")

            var mhs = MockDB.listMhs.find { m-> m.nrp==nrp }
            if(mhs!=null){
                var selectedPosition:Int = when(mhs.jurusan){
                    11 -> 0
                    18 -> 1
                    17 -> 2
                    10 -> 3
                    else-> 4
                }
                etNRP.setText(nrp)
                etNama.setText(mhs.nama)
                spinnerJurusan.setSelection(selectedPosition)
                etNRP.isEnabled = false
            }

        }

        buttonAddMahasiswa.setOnClickListener {
            var nrp:String = etNRP.text.toString()
            var nama:String = etNama.text.toString()
            var jurusanString: String = spinnerJurusan.selectedItem.toString()
            var jurusan:Int = when(jurusanString){
                "Informatika"->11
                "SIB"->18
                "DKV"->17
                "Elektro"->10
                else-> 1
            }
            if(mode=="INSERT"){
                val newMhs = Mahasiswa(nrp, nama, jurusan)
                MockDB.addMahasiswa(newMhs)
            }else{
                var indexMhs = -1
                for ((index, mhs) in MockDB.listMhs.withIndex()){
                    if(mhs.nrp==nrp){
                        indexMhs = index
                    }
                }
                if(indexMhs>-1){
                    MockDB.listMhs[indexMhs].nama = nama
                    MockDB.listMhs[indexMhs].jurusan = jurusan
                }
            }
            finish()
        }

    }
}