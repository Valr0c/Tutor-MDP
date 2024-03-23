package com.example.tutorm5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Spinner
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class CreateFragment : Fragment() {
    lateinit var buttonAddMahasiswa: Button
    lateinit var etNRP: EditText
    lateinit var etNama: EditText
    lateinit var tvMode: TextView
    lateinit var spinnerJurusan: Spinner
    var mode:String = "INSERT"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ini untuk menerima argument, mirip seperti menerima dari intent
        val paramNrpEdit = CreateFragmentArgs?.fromBundle(arguments as Bundle)?.nrpEdit
        etNRP = view.findViewById(R.id.etNRP)
        etNama = view.findViewById(R.id.etNama)
        tvMode = view.findViewById(R.id.tvMode)
        buttonAddMahasiswa = view.findViewById(R.id.buttonAddMahasiswa)
        spinnerJurusan = view.findViewById(R.id.spinnerJurusan)
        var spinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),android.R.layout.simple_spinner_item,MockDB.listJurusan)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerJurusan.adapter = spinnerAdapter
        if(paramNrpEdit!=" "){ //karena kita beri default nilai spasi untuk null args
            //mode edit
            mode = "UPDATE"
            buttonAddMahasiswa.text = "Edit"
            tvMode.text = "Edit Mahasiswa"
            var mhs = MockDB.listMhs.find { m-> m.nrp==paramNrpEdit }
            if(mhs!=null){
                var selectedPosition:Int = when(mhs.jurusan){
                    11 -> 0
                    18 -> 1
                    17 -> 2
                    10 -> 3
                    else-> 4
                }
                etNRP.setText(paramNrpEdit)
                etNama.setText(mhs.nama)
                spinnerJurusan.setSelection(selectedPosition)
                etNRP.isEnabled = false
            }
        }
        buttonAddMahasiswa.setOnClickListener{
            var nrp:String = etNRP.text.toString()
            var nama:String = etNama.text.toString()
            var jurusanString: String = spinnerJurusan.selectedItem.toString()
            var jurusan = MockDB.toNumJurusan(jurusanString)
            if(mode=="INSERT"){
                val newMhs = Mahasiswa(nrp, nama, jurusan)
                MockDB.addMahasiswa(newMhs)
                Toast.makeText(activity, "Berhasil tambah mahasiswa", Toast.LENGTH_SHORT).show()
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
                
                Toast.makeText(activity, "Berhasil ubah data mahasiswa", Toast.LENGTH_SHORT).show()
            }
            findNavController().navigate(R.id.action_global_homeFragment)
        }
    }

}