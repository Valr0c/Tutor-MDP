package com.example.tutorminggu6

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SkillViewModel:ViewModel() {
    var skills:MutableLiveData<ArrayList<Skill>> = MutableLiveData(arrayListOf(
        Skill("Normal Attack",10,0),
        Skill("Explosion",100,50),
    ))
    var selectedSkill:MutableLiveData<Skill?> = MutableLiveData(null)

    fun addSkill(name:String, damage:Int, mana:Int){
        skills.value!!.add(Skill(name, damage, mana))
    }

    fun updateSkill(name:String, damage:Int, mana:Int){
        val index = skills.value!!.indexOfFirst {
            it.name == selectedSkill.value!!.name
        }
        if (index >= 0) {
            skills.value!![index] = Skill(name, damage, mana)
        }
    }

    fun deleteSkill(skill:Skill){
        val index = skills.value!!.indexOfFirst {
            it.name == skill.name
        }
        if (index >= 0) {
            Log.d("Cek index Delete", index.toString())
            skills.value!!.removeAt(index)
        }
    }
}