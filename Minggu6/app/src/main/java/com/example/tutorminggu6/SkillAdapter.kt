package com.example.tutorminggu6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SkillAdapter(
    var listSkill: ArrayList<Skill>,
    var onDelete: ((Skill) -> Unit)? = null,
    var onEdit: ((Skill) -> Unit)? = null,
):RecyclerView.Adapter<SkillViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.skill_item, parent, false)
        return SkillViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return listSkill.size
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        val data = listSkill[position]
        holder.tvName.text = data.name
        holder.tvMana.text = "Mana: ${data.mana}"
        holder.tvDamage.text = "Damage: ${data.damage}"
        holder.btEdit.setOnClickListener {
            onEdit?.invoke(data)
        }
        holder.btDelete.setOnClickListener {
            onDelete?.invoke(data)
        }
    }
}

class SkillViewHolder(val row: View):RecyclerView.ViewHolder(row){
    val tvName = row.findViewById<TextView>(R.id.tvNamaSkill)
    val tvMana = row.findViewById<TextView>(R.id.tvManaSkill)
    val tvDamage = row.findViewById<TextView>(R.id.tvDamageSkill)
    val btDelete = row.findViewById<Button>(R.id.btDeleteSkill)
    val btEdit = row.findViewById<Button>(R.id.btEditSkill)
}