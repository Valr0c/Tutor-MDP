package com.example.tutorminggu6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorminggu6.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding:ActivityMain3Binding
    private val vm:SkillViewModel by viewModels()
    var mode = "INSERT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main3)

        val skillAdapter = SkillAdapter(vm.skills.value!!)
        skillAdapter.onDelete = {
            vm.deleteSkill(it)
            skillAdapter.notifyDataSetChanged()
        }
        skillAdapter.onEdit = {
            vm.selectedSkill.value = it
            skillAdapter.notifyDataSetChanged()
        }
        binding.rvSkill.adapter = skillAdapter
        binding.rvSkill.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.btSubmit.setOnClickListener {
            val newName = binding.etName.text.toString() ?: ""
            val newDamage = binding.etDamage.text.toString().toInt() ?: 0
            val newMana = binding.etMana.text.toString().toInt() ?: 0
            // Disini kita tidak lagi melakukan business logic pada Insert dan Update
            when(mode){
                "INSERT" -> {
                    vm.addSkill(newName, newDamage,newMana)
                }
                "UPDATE" -> {
                    vm.updateSkill(newName, newDamage, newMana)
                }
            }
            //Kita memiliki selectedSkill yang akan mengisi EditText
            //Pada saat value null, maka nilai pada setiap editText akan kosong
            vm.selectedSkill.value = null
            skillAdapter.notifyDataSetChanged()
            mode = "INSERT"
        }

        //Observer untuk selected skill, kalau di edit/selesai menambah
        val selectedSkillObserver = Observer<Skill?>{
            mode = if(it == null) "INSERT" else "UPDATE"

            binding.skill = it
        }
        // saat terjadi perubahan value pada selectedSkill
        // selectedSkill akan dioper ke variabel binding
        // Hal ini memungkinkan perubahan secara otomatis
        vm.selectedSkill.observe(this, selectedSkillObserver)

        binding.btBack.setOnClickListener {
            finish()
        }
    }
}