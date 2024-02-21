package com.example.minggu2

class Ayam(hargaBeli:Int=10000, hargaJual:Int=20000) : Hewan(hargaBeli = hargaBeli,
    hargaJual = hargaJual, jumlahKaki = 2 ) {

    constructor(hargaBeli: Int, hargaJual: Int, berat: Int, nama:String) : this(hargaBeli, hargaJual) {
        namaHewan = nama
        this.berat = berat
    }
    //untuk override toString
    override fun toString(): String{
        return "Ayam dengan berat $berat kg memiliki harga beli ${hargaBeli.toRupiah()}, harga " +
                "jual ${hargaJual.toRupiah()}, dan jumlah kaki $jumlahKaki"
    }
    override fun hasilHewan():String{
        return "telur"
    }
}