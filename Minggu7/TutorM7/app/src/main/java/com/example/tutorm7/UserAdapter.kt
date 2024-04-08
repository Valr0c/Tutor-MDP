package com.example.tutorm7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter (
    val data: MutableList<UserEntity>,
    var onEditClickListener:((UserEntity)->Unit)? = null,
    var onDeleteClickListener:((UserEntity)->Unit)? = null,
): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row){
        val txtData: TextView = row.findViewById(R.id.txtData)
        val txtGender: TextView = row.findViewById(R.id.txtGender)
        val btnEdit: Button = row.findViewById(R.id.btnEdit)
        val btnDelete: Button = row.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(
            R.layout.user_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val u = data[position]
        holder.txtData.text = "${u.username} ${u.name}"
        holder.txtGender.text = u.gender

        holder.btnDelete.setOnClickListener{
            onDeleteClickListener?.invoke(u)
        }
        holder.btnEdit.setOnClickListener{
            onEditClickListener?.invoke(u)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}