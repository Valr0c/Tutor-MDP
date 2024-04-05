package com.example.tutorm7

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Entity ini ibarat sebuah tabel dalam database. Sebuah entity wajib ada annotation @Entity nya
disertai dengan nama table

Variabel lain akan dianggap sebuah column dalam database dengan nama variabel itu sendiri sebagai
nama column

Jika dilihat, disini kita membuat auto generate pada primary key menjadi false
Artinya, kita harus menyediakan primary key kita sendiri
Room akan mengganggap bahwa field pertama setelah @PrimaryKey akan dijadikan primary key
Pada kasus ini, kolom username akan dijadikan primary key

Jika kita membuat username menjadi primary key, maka pada saat update
kita tidak akan dapat merubah value dari username tersebut

Jika username dapat diubah, maka berikan sebuah kolom lain yang akan dijadikan primary key
atau buat @PrimaryKey(autoGenerate = true)
Disini Room akan membuatkan primary keynya sendiri
 */

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "username") val username:String,
    @ColumnInfo(name = "password") var password:String,
    @ColumnInfo(name = "name") val name:String,
){
    override fun toString(): String {
        return "$name - $username"
    }
}
