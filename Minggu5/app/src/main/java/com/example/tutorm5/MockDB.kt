package com.example.tutorm5

class MockDB {
    companion object{
        val listMhs = mutableListOf(
            Mahasiswa("220181000", "Andi", 18),
            Mahasiswa("220181002", "Abdi", 18),
            Mahasiswa("220111001", "Budi", 11),
            Mahasiswa("220101002", "Chandra", 10),
            Mahasiswa("220171003", "Danu", 17),
        )
        val listJurusan:ArrayList<String> = arrayListOf("Informatika", "SIB", "DKV", "Elektro", "Lainnya")
        fun addMahasiswa(m:Mahasiswa){
            listMhs.add(m)
        }
        fun toNumJurusan(jurusan:String):Int{
            //untuk merubah jurusan string jadi kode jurusan
            return when(jurusan){
                "Informatika"->11
                "SIB"->18
                "DKV"->17
                "Elektro"->10
                else-> 1
            }
        }
        fun toStringJurusan(jurusan:Int):String{
            //untuk merubah jurusan string jadi kode jurusan
            return when(jurusan){
                11->"Informatika"
                18->"SIB"
                17->"DKV"
                10->"Elektro"
                else->"Lainnya"
            }
        }
    }
}