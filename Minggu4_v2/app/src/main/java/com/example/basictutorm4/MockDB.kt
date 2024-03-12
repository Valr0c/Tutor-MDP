package com.example.basictutorm4

class MockDB {
    companion object{
        val listMhs = mutableListOf(
            Mahasiswa("220181000", "Andi", 18),
            Mahasiswa("220181000", "Abdi", 18),
            Mahasiswa("220111001", "Budi", 11),
            Mahasiswa("220101002", "Chandra", 12),
            Mahasiswa("220171003", "Danu", 17),
        )
        fun addMahasiswa(m:Mahasiswa){
            listMhs.add(m)
        }
    }
}