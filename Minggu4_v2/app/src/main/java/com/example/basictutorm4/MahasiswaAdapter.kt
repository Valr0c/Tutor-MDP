package com.example.basictutorm4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// adapter perlu mengextend class RecyclerView.Adapter<ViewHolder>
// viewholder digunakan sebagai penampung view dalam layout
// serta menggabungkan data dengan view
// view holder berada didalam class Adapter
class MahasiswaAdapter (
     val data: MutableList<Mahasiswa>,
     val layout: Int,
     var onEditClickListener: ((Mahasiswa)-> Unit)
): RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>() {
    // custom class yang mengextend ViewHolder
    // digunakan untuk menambahkan property yang berisi view
    // yang didapatkan dari layout
    class ViewHolder(val row:View) : RecyclerView.ViewHolder(row){
        val nrpTv:TextView = row.findViewById(R.id.tvNRP)
        val namaTv:TextView = row.findViewById(R.id.tvNama)
        val jurusanTv:TextView = row.findViewById(R.id.tvJurusan)
        val btnEditList:ImageButton = row.findViewById(R.id.btnEditList)
        val btnDeleteList:ImageButton = row.findViewById(R.id.btnDeleteList)
    }

    // on create view holder dipanggil setiap kali ada list item baru ditambahkan
    // atau ada data terupdate dengan notifyDataSetChanged
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return ViewHolder(itemView.inflate(
            layout, parent ,false
        ))
    }

    // saat viewholder sudah dibuat, pada bagian ini kita gabungkan view
    // dengan data yang kita miliki
    // position berdasarkan index pada data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mhs = data[position]
        holder.nrpTv.text = mhs.nrp
        holder.namaTv.text = mhs.nama
        holder.jurusanTv.text = when(mhs.jurusan){
            11 -> "Informatika"
            18 -> "SIB"
            17 -> "DKV"
            10 -> "Elektro"
            else -> "Lainnya"
        }
        holder.btnDeleteList.setOnClickListener{
            data.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition) //ini untuk memberitahu adapter bahwa ada item yang dihapus
        }
        holder.btnEditList.setOnClickListener{
            onEditClickListener.invoke(mhs)
        }
    }

    // digunakan untuk mengetahui ukuran dari list view yang akan di iterasikan
    override fun getItemCount(): Int {
        return data.size
    }
}