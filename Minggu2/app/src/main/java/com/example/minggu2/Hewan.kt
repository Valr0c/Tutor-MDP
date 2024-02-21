package com.example.minggu2

//keyword open digunakan agar class ini bisa diturunkan
//kita juga bisa memberikan default value untuk
open class Hewan(var hargaBeli: Int=9000, var hargaJual: Int=12000, val jumlahKaki: Int, var
berat:Int = 50 ) {

//    init {
//        /**method init ini bertindak seperti constructor
//        kita bisa set logic di sini, nantinya bagian kode ini akan terpanggil saat ada
//        inisialisasi class**/
//        println("ada hewan baru dibuat")
//    }

    var profitJual:Int = 0
        //custom getter
        get() {
            return hargaJual - hargaBeli
        }
    //atau dibuat menjadi expression
    // get() = hargaJual - hargaBeli
    var namaHewan:String = ""
    var kodeHewan:String = ""
        get(){
            return "H-${namaHewan.substring(0,1)}-$nomorKodeHewan"
        }
    var nomorKodeHewan:Int = 0
        set(urutan){
            field = urutan + 1 //field disini merujuk pada propertinya, yaitu nomorKodeHewan
        }

    //untuk override toString, kalau tidak di override, waktu diprint akan keluar hash code object seperti com.example.myapplication.Hewan@1d44bcfb
    override fun toString(): String{
        return "Hewan $namaHewan dengan berat $berat kg memiliki harga beli ${hargaBeli.toRupiah()}, " +
                "harga jual ${hargaJual.toRupiah()}, dan jumlah kaki $jumlahKaki"
    }

    //method yang mau dibuat override juga perlu di-open
    open fun hasilHewan(): String {
        return "Hasil panen hewan"
    }

    companion object {
        //ini static method / properties, sehingga bisa diakses tanpa perlu instansiasi
        var totalJumlahHewan: Int = 0

        fun addTotalJumlahHewan(num:Int){
            totalJumlahHewan+=num
        }
    }
}