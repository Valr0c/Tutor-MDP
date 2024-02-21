package com.example.minggu2

//inheritance harus mengacu pada class yang memiliki keyword "open"
class Anjing(berat:Int) : Hewan(berat = berat, jumlahKaki = 4 ), HewanDarat {
    //untuk override toString

    // kita bisa memberikan secondary constructor
    //keyword this(berat) itu akan memanggil constructor utamanya
    constructor(berat: Int, nama:String) : this(berat) {
        namaHewan = nama
    }
    override fun toString(): String{
        return "Anjing $namaHewan dengan berat $berat kg memiliki harga beli ${hargaBeli.toRupiah()}, " +
                "harga jual ${hargaJual.toRupiah()}, dan jumlah kaki $jumlahKaki"
    }
    override fun hasilHewan():String{
        return "Anjing tidak dapat dipanen"
    }

    override fun bersuara(): String {
        return "woof woof"
    }
}