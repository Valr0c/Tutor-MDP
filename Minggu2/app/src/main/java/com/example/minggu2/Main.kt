package com.example.minggu2

//TUTOR M2 - Kotlin OOP (Class and Object)
/**Untuk mengingat ulang materi pbo,
Class = sebagai blueprint object
Object = instance dari class
 **/
fun basicTutor(){
    //Berikut adalah beberapa cara membuat instance object
    val hewan1:Hewan = Hewan(3000,5000, jumlahKaki = 2)
    val hewan2:Hewan = Hewan(hargaJual = 1500, hargaBeli = 900, jumlahKaki = 10)

    //coba print, ini sudah dioverride untuk method tostring
//    println(hewan1)
//    println(hewan2)
//    println(hewan3)

    //untuk getter setter bisa langsung diakses dengan NamaClass.Properti
    hewan1.hargaBeli = 10
//    print(hewan1.hargaBeli)

    //apabila parameter pada constructor diset sebagai val, maka properti tsb tidak bisa diset lagi
//    hewan1.jumlahKaki = 20 //pasti error Val cannot be reassigned

    //coba panggil custom getter untuk mendapatkan profit jual hewan
    println(hewan2.profitJual)

    hewan2.namaHewan = "Malika"
    hewan2.nomorKodeHewan = 0
    println(hewan2.kodeHewan)

    val anjing1:Anjing = Anjing(30)
    println(anjing1)
    anjing1.hargaBeli = 1100 //bisa langsung set properti parent class seperti ini
    anjing1.hargaJual = 2000
    anjing1.namaHewan = "Samoyed"
    anjing1.nomorKodeHewan = 1
    println(anjing1.kodeHewan)
    println(anjing1.hasilHewan())

    val ikan1:Ikan = Ikan()
    ikan1.namaHewan = "Nemo"

    //konsep polymorphism, dimana semua instance di atas merupakan instance dari class Hewan,
    // maupun subclass / turunan dari class hewan
    val listHewanDummy: ArrayList<Hewan> = arrayListOf(hewan1, hewan2, anjing1, ikan1)

    //untuk cek apakah object ini merupakan instance tertentu, bisa menggunakan is
    for (i in 0..listHewanDummy.size-1){
        val h = listHewanDummy[i]
        if(h is Anjing){
            println("Hewan ${h.namaHewan} adalah anjing")
        }else if(h is Ikan){
            println("Hewan ${h.namaHewan} adalah ikan")
        }else{
            println("Hewan ${h.namaHewan} adalah hewan selain anjing dan ikan")
        }
    }
}
fun main(args: Array<String>) {
//    basicTutor()
    //studi kasus
    var inpMenu:String = "0"

    val listHewan: ArrayList<Hewan> = arrayListOf()
    val golden:Anjing = Anjing(30)
    golden.namaHewan = "Golden"
    val iwak:Ikan= Ikan()
    iwak.namaHewan = "Kowalski"
    listHewan.add(golden)
    listHewan.add(iwak)
    Hewan.addTotalJumlahHewan(2)

    while(inpMenu!="6"){
        println("---Simple Farm---")
        println("1. Lihat List Hewan")
        println("2. Lihat Harga Beli & Jual Hewan")
        println("3. Lihat Hasil Panen Hewan")
        println("4. Lihat Deskripsi Hewan")
        println("5. Tambah Hewan Baru")
        println("6. Exit")
        print(">> ")
        inpMenu = readln()
        if(inpMenu=="1"){
            println("List Hewan :")
            for (i in 0..listHewan.size-1){
                val h = listHewan[i]
                print("${(i+1)}. ${h.namaHewan} ")
                when (h) {
                    is Anjing -> {
                        println("- anjing")
                    }

                    is Ikan -> {
                        println("- ikan")
                    }

                    is Ayam -> {
                        println("- ayam")
                    }

                    else -> {
                        println("- hewan lain")
                    }
                }
            }
            println("Total hewan "+Hewan.totalJumlahHewan)
        } else if(inpMenu=="2"){
            println("Harga Beli & Jual Hewan:")
            for(h in listHewan){
                println(h)
            }
        } else if(inpMenu=="3"){
            println("Hasil Panen Hewan:")
            for(i in 0..listHewan.size-1){
                print("${(i+1)}. ")
                cetakHasilHewan(listHewan[i])
            }
        } else if(inpMenu=="4"){
            println("Deskripsi Hewan:")
            for(i in 0..listHewan.size-1){
                print("${(i+1)}. ")
                val h = listHewan[i]
                when (h) {
                    is HewanDarat -> {
                        println(h.bersuara())
                    }

                    is HewanAir -> {
                        println(h.berenang())
                    }

                    is Ayam ->{
                        println(h.berkokok()) //extend function
                    }
                    else -> {
                        println("Tidak ada deskripsi")
                    }
                }
            }
        } else if(inpMenu=="5"){
            println("Tambah Hewan Baru")
            println("1. Tambah Anjing")
            println("2. Tambah Ayam")
            println("3. Tambah Ikan")
            println("4. Hewan lain")
            print(">> ")
            var tipe = readln()
            print("Nama : ")
            var nama = readln()
            print("Berat : ")
            var beratStr = readln()
            var berat = 0
            berat = beratStr.toIntOrNull() ?: 10 //ini untuk safe parse string ke integer, bisa
            // dikasih elvis operator untuk default value jika error

            val hewanBaru = hewanBaru(tipe, nama, berat)
            listHewan.add(hewanBaru)
            Hewan.totalJumlahHewan += 1
        }
    }
}
fun cetakHasilHewan(h:Hewan){
    println(h.hasilHewan())
}
fun hewanBaru(tipe:String, nama:String, berat:Int): Hewan {
    //kita juga bisa pakai return when seperti ini, perhatikan bahwa sintaks ini memerlukan
    // bagian else, jika tidak maka hasilnya error
    return when(tipe){
        "1" -> Anjing(nama = nama, berat = berat)
        "2" -> Ayam(hargaBeli = 10000, hargaJual = 20000, nama = nama, berat = berat)
        "3" -> Ikan(berat, nama)
        else -> Hewan(jumlahKaki = 2)
    }
}

//kita juga bisa extend function seperti di bawah ini, bisa digunakan apabila kita tidak mau
// memodifikasi class, namun memerlukan method tambahan untuk menjalankan logic tertentu
fun Int.toRupiah():String{
    return "Rp.$this"
}
fun Ayam.berkokok():String{
    return "${this.namaHewan} kukuruyuk"
}