package tutor.m1

/**
 * Kotlin menyimpan file code pada path app/src/main/java
 * semua code kotlin akan disimpan pada package path tersebut
 * Untuk merun code yang sudah dibuat pada console, kita perlu melakukan konfigurasi
 *
 * 1. Buat dulu Kotlin File pada app/src/main/java/<package>/
 * 2. Untuk melakukan konfigurasi, pilih dropdown dengan tulisan app pada bagian atas
 * 3. Kemudian pilih Edit Configurations
 * 4. Pada sisi kiri, klik tombol plus, dan tambahkan config untuk Kotlin
 * 5. pilih dulu classpath of module sehingga mengikuti <nama_package>.<nama_class>
 * 6. Beri nama dan pilih main.kt pada bagian Main class
 * 7. Kemudian klik OK
 *
 * Untuk menjalankan code, klik tombol Run
 *
 */

//  tidak perlu lagi pakai titik koma (;) di akhir baris :D
fun main() {
    // untuk membuat variable, kita perlu menuliskan tipe data
    val bahasa:String = "Kotlin"
    val ip:Float = 3.75f

    // kotlin juga bisa otomatis mengenali tipe data dengan smart cast
    val _IDE = "Android Studio"    // otomatis String
    var kkm = 55    // otomatis integer
    kkm = 50 // bisa diubah karena var

    val pilihan_bahasa = """
        Android Studio
         - Kotlin
         - Java
    """

    println("Hello World!") // untuk print ke console
    print("Hello World!") // untuk print ke console tanpa newline

    println('a' + 1) // hati hati saat menambahkan tipe data yang berbeda
    //  println(1 + 'a')  // bisa error apabila smart cast kotlin tidak bisa menentukan tipe data
    println('a' + 1.toString())

    println("Belajar $bahasa menggunakan ${_IDE}")

    // operator
    println("== Belajar Operator ==")
    println(3 + 2)
    println(3 - 2)
    println(3 * 2)
    println(3 / 2)
    println(3 * 1.0 / 2)

    println("== Belajar Condition ==")
    if(ip > 4){
        println("Sempurna")
    }
    else if(ip > 2){
        println("Lulus")
    }
    else{
        println("Gagal")
    }

    println("Penggunaan when")
    when (ip) {
        4.0f -> println("Sempurna")
        in 2.0f..3.0f -> println("Lulus")
        1.9f -> println("Lulus bersyarat")
        else -> println("Gagal")
    }

    println("== Belajar Looping ==")

    println("Looping biasa")
    for(i in 1..10){
        println(i)
    }

    print("Looping array")
    val asisten = arrayListOf<String>("Mikhael", "Kedrick", "Thio")
    for (a in asisten){
        println(a)
    }

    print("Looping array dengan index")
    for ((idx, a) in asisten.withIndex()){
        println("$idx. $a")
    }

    println("Looping dengan step")
    for(i in 1..10 step 2){
        println(i)
    }

    println("Looping dengan reverse")
    for (i in 10 downTo 1 step 2){
        println(i)
    }

    println("Bisa juga looping huruf")
    for (i in 'a'..'e'){
        println(i)
    }

    println("Looping while")
    var a = 10
    while(a > 0){
        println(a)
        a--
    }

    println("Looping do while")
    a = 10
    do {
        println(a)
        a--
    } while(a > 0)

    println("Looping repeat")
    repeat(5){
        println("Repeat ke $it")
    }

    println("== Belajar List ==")
    println("LIST (Tidak bisa di add/remove dan read only)")
    val hari = listOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")
    println(hari)
    // hari[0] = "Minggu" //error karena read only

    println("MutableList & ArrayList (Bisa di add/remove)")
    println("ArrayList merupakan implementation dari MutableList")
    val todo = mutableListOf("Kerja tugas praktikum", "Belajar materi minggu depan", "Cari makan", "Tidur")
    // val todo = arrayListOf("Kerja tugas praktikum", "Belajar materi minggu depan", "Cari makan", "Tidur")
    println(todo)
    todo.add("Mandi")
    todo.removeAt(0)
    println(todo)
    todo[0] = "Belajar materi praktikum minggu depan"
    println(todo[0])

    println("Array (Tidak bisa di add/remove)")
    val angka = arrayOf(1, 2, 3, 4, 5)
    println(angka[0])
    angka[0] = 10
    println(angka[0])

    // Penggunaan null bisa digunakan dengan memberi tanda tanya pada tipe data
    var pilihan: Int? = null

    // saat melakuakn print, kita bisa hanya print apabila ada valuenya dengan 2 cara
    // 1. menggunakan if

    if(pilihan != null){
        println(pilihan)
    }

    // 2. menggunakan safe call
    println(pilihan?.toString())

    // 3. elvis operator, menggunakan value pengganti apabila null
    println(pilihan ?: "Tidak ada pilihan")

    // kalau pasti tidak null, kita bisa menggunakan !! operator
    pilihan = 0
    println(pilihan!!.toString())

    // Terima Input
    print("Masukkan nama anda:")
    val nama = readLine()
    println("Halo $nama")

    // Terima Input dengan tipe data
    print("Masukkan umur anda:")
    val umur = readLine()?.toInt()
    println("Umur anda $umur tahun")

    // fungsi oneline
    val isDewasa = if(umur!! > 17) "Dewasa" else "Anak-anak"
    println(isDewasa)

    println("Tahun lahir: ${getTahunLahir(umur)}")
    welcome()
    welcome("Mikhael")

    // bisa optional parameter dengan tambah tanda tanya dan default value
    println(hitungTotal(arrayListOf(1000, 2000, 3000), 1000))
    println(hitungTotal(arrayListOf(1000, 2000, 3000)))

    // named argument
    println(deskipsi("Mikhael", ip=3.8f, umur=20, jurusan="Informatika"))

    // lambda function
    val jumlah = {a: Int, b: Int -> a + b}
    println(jumlah(1, 2))

    // lambda function dengan tipe
    val kurang: (Int, Int) -> Int = {a, b -> a - b}
    println(kurang(2, 1))

    val data = arrayListOf(10,2,5,3,7,8,1)
    val data_baru = modif(data) { it * 2 }
    println("Data baru")
    println(data_baru)

    val data_genap = data.filter{ it % 2 == 0}
    println("Data genap")
    println(data_genap)

    val data_ribu = data.map{ it * 1000}
    println("Data ribu")
    println(data_ribu)

    val data_nol = data.find{ it == 0 }
    println("Ini adalah data $data_nol")

    //Data disini bernilai null karena pada saat menggunakan find,
    // data dengan kondisi yang diberikan tidak ada
    // kita bisa menggunakan elvis operator untuk memberikan nilai default
    println("Ini adalah data ${data_nol ?: 0}")

    print("Masukkan string dengan spasi:")
    var cmd = readLine()
    print(cmd)
    for (i in cmd!!.split(' ')){
        println(i)
    }
}

fun getTahunLahir(umur: Int): Int{
    return 2024 - umur
}

fun welcome(nama: String = "Pengguna"): Unit {
    println("Selamat datang, $nama")
}

fun hitungTotal(jumlah: ArrayList<Int>, diskon: Int? = null): Int{
    var total = 0
    for (j in jumlah){
        total += j
    }
    if(diskon != null){
        total -= diskon
    }
    return total
}

fun deskipsi(nama: String, umur: Int, jurusan:String, ip: Float?=0f): String{
    return """
        Nama: $nama
        Umur: $umur
        Jurusan: $jurusan
        IP: $ip
    """
}

fun modif(angka: ArrayList<Int>, modifier: (Int) -> Int): ArrayList<Int>{
    val hasil = arrayListOf<Int>()
    for (a in angka){
        hasil.add(modifier(a))
    }
    return hasil
}