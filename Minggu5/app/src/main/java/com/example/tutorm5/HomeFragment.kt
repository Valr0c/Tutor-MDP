package com.example.tutorm5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class HomeFragment : Fragment() {
    lateinit var btnToAdd: Button
    lateinit var rvMahasiswa: RecyclerView
    lateinit var mhsAdapter: MahasiswaAdapter
    lateinit var layoutManager: LayoutManager
    lateinit var btnSort:Button
    var sortMode: Int = 1
    //method untuk melakukan inflate layout ke fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    //ini dijalankan sesudah onCreateView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnToAdd = view.findViewById(R.id.btnToAdd)
        btnSort = view.findViewById(R.id.btnSort)
        rvMahasiswa = view.findViewById(R.id.rvMahasiswa)

        //require context akan mendapatkan context yang memanggil fragment ini
        layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        mhsAdapter = MahasiswaAdapter(MockDB.listMhs) { mhs ->
            //untuk memberikan arguments pada action navigation dapat dilakukan seperti ini
            val action = HomeFragmentDirections.actionHomeFragmentToCreateFragment(mhs.nrp)
            findNavController().navigate(action)
        }
        //JANGAN DIBUAT SEPERTI INI, RV TIDAK AKAN MUNCUL
//        rvMahasiswa.apply {
//            this.layoutManager = layoutManager
//            this.adapter = mhsAdapter
//        }
        rvMahasiswa.adapter = mhsAdapter
        rvMahasiswa.layoutManager = layoutManager

        btnToAdd.setOnClickListener {
            findNavController().navigate(R.id.action_global_createFragment)
        }

        btnSort.setOnClickListener {
            if(sortMode==1){
                sortMode = 0
                MockDB.listMhs.sortByDescending {
                        mhs-> mhs.nrp + mhs.nama
                }
                btnSort.text = "Sort Ascending"
            }else {
                sortMode = 1
                MockDB.listMhs.sortBy {
                        mhs-> mhs.nrp + mhs.nama
                }
                btnSort.text = "Sort Descending"
            }
            mhsAdapter.notifyDataSetChanged()
        }
    }

}