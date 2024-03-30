package com.example.tutorminggu6

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {
    /**
     * LiveData merupakan sebuah Wrapper class yang dapat mengirim notifikasi saat terjadi perubahan data
     * MutableLiveData merupakan bentuk LiveData yang dapat diubah ubah secara langsung
     *
     * Dalam praktek kenyataannya, LiveData akan menjadi getter dan ViewModel akan memiliki
     * private MutableLiveData yang menjadi value utama
     *
     * Dengan memanfaatkan LiveData, setiap data yang ditampilkan pada layout
     * akan berubah secara otomatis saat nilai berubah
     *
     * Selain menyimpan Tipe Data Primitf, LiveData juga dapat menyimpan class
     */
    private val _health:MutableLiveData<Int> = MutableLiveData(0)
    val health:LiveData<Int>
        get() = _health

    private val _mana:MutableLiveData<Int> = MutableLiveData(0)
    val mana:LiveData<Int>
        get() = _mana

    private val _attack:MutableLiveData<Int> = MutableLiveData(0)
    val attack: LiveData<Int>
        get() = _attack

    private var baseHealth = 5
    private var baseMana = 5
    private var baseAttack = 5

    /**
     * Penggunaan MutableLiveData secara langsung akan menyebabkan
     * program perlu melakukan null checking saat menggunakan operasi
     */
    var strength:MutableLiveData<Int> = MutableLiveData(0)
    var intelligence:MutableLiveData<Int> = MutableLiveData(0)
    var dexterity:MutableLiveData<Int> = MutableLiveData(0)

    var poin:MutableLiveData<Int> = MutableLiveData(10)

    /**
     * Stat Increment
     */
    fun increaseStr(){
        if(poin.value!! > 0){
            strength.value = strength.value?.plus(1)
            poin.value = poin.value?.minus(1)
            updateStat()
        }
    }
    fun increaseInt(){
        if(poin.value!! > 0){
            intelligence.value = intelligence.value?.plus(1)
            poin.value = poin.value?.minus(1)
            updateStat()
        }
    }
    fun increaseDex(){
        if(poin.value!! > 0){
            dexterity.value = dexterity.value?.plus(1)
            poin.value = poin.value?.minus(1)
            updateStat()
        }
    }

    /**
     * Stat Decrement
     */
    fun decreaseStr(){
        if(strength.value!! > 0){
            strength.value = strength.value!! - 1
            poin.value = poin.value?.plus(1)
            updateStat()
        }
    }
    fun decreaseInt(){
        if(intelligence.value!! > 0){
            intelligence.value = intelligence.value?.minus(1)
            poin.value = poin.value?.plus(1)
            updateStat()
        }
    }
    fun decreaseDex(){
        if(dexterity.value!! > 0){
            dexterity.value = dexterity.value?.minus(1)
            poin.value = poin.value?.plus(1)
            updateStat()
        }
    }

    fun changeClass(newClass:String){
        when(newClass){
            "NOCLASS" -> {
                baseHealth = 5
                baseMana = 5
                baseAttack = 5
            }
            "WARRIOR" -> {
                baseHealth = 6
                baseMana = 2
                baseAttack = 7
            }
            "PALADIN" -> {
                baseHealth = 8
                baseMana = 4
                baseAttack = 3
            }
            "MAGE" -> {
                baseHealth = 3
                baseMana = 8
                baseAttack = 4
            }
        }
        updateStat()
    }

    fun updateStat(){
        _health.value = baseHealth + strength.value!! + 2 * dexterity.value!!
        _mana.value = baseMana + 3 * intelligence.value!!
        _attack.value = baseAttack + 3 * strength.value!! + dexterity.value!! + intelligence.value!!
    }
}