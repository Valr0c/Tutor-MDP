package com.example.minggu2

//kita bisa implement interface pada class yang merupakan hasil extend
//perlu diperhatikan, semua method yang ada pada interface wajib ada pada class anakannya
class Ikan(): Hewan(jumlahKaki = 0), HewanAir {

    constructor(berat: Int, nama:String) : this() {
        namaHewan = nama
        this.berat = berat
    }

    override fun berenang(): String {
        return "$namaHewan blubub blubub"
    }

    override fun toString(): String {
        return super.toString()
    }
}