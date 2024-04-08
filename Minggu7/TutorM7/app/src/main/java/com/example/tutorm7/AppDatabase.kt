package com.example.tutorm7

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.coroutines.coroutineContext

/*
AppDatabase merupakan file yang digunakan untuk menginisiasi sebuah database dalam
local storage android.
File ini nyimpen variabel2 DAO yang kita buat ke depannya

Pertama, ada annotation(@)Database di mana di dalamnya ini kita isi array of entity.
Entity ini kayak sebuah tabel dalam database yang representasinya di kotlin bakal jadi sebuah
class .kt. Jadi semua Entity yang hendak digunakan dan disimpan dalam database ditaruh di
@Database
 */

@Database(entities = [UserEntity::class], version = 1)
// version itu penting karena nanti ketika kita update database kita bisa update versi database
// perubahan version menyebabkan database local di device user akan diupdate
abstract class AppDatabase: RoomDatabase(){
    // listkan semua DAO yg digunakan dlm project
    // jika menggunakan lebih dari 2 tabel, list semua Dao nya
    abstract fun userDao(): UserDao

}

