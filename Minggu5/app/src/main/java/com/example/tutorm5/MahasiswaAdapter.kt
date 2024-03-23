package com.example.tutorm5

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MahasiswaAdapter (
    val data: MutableList<Mahasiswa>,
    var onEditClickListener: ((Mahasiswa)-> Unit)
): RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>() {
    class ViewHolder(val row:View) : RecyclerView.ViewHolder(row){
        val nrpTv:TextView = row.findViewById(R.id.tvNRP)
        val namaTv:TextView = row.findViewById(R.id.tvNama)
        val jurusanTv:TextView = row.findViewById(R.id.tvJurusan)
        val btnEditList:ImageButton = row.findViewById(R.id.btnEditList)
        val btnDeleteList:ImageButton = row.findViewById(R.id.btnDeleteList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return ViewHolder(itemView.inflate(
            R.layout.mahasiswa_item, parent ,false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mhs = data[position]
        holder.nrpTv.text = mhs.nrp
        holder.namaTv.text = mhs.nama
        holder.jurusanTv.text = MockDB.toStringJurusan(mhs.jurusan)
        holder.btnDeleteList.setOnClickListener{
            data.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
        }
        holder.btnEditList.setOnClickListener{
            onEditClickListener.invoke(mhs)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}