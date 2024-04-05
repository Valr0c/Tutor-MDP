package com.example.tutorm7

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/*
DAO ini interface yang jadi perantara kotlin dengan database.
Isinya method2 yang digunakan untuk melakukan query ke database
Untuk command basic kyk insert, update, dan delete bisa pake annotation
@Insert, @Update, dan @Delete dengan objek yang mau diinsert/update/delete yang dipassing sebagai
parameternya.

Kalo query2 yang bersifat lebih spesifik, bisa pakai @Query dengan isi querynya sebagai
parameter functionnya
 */

@Dao
interface UserDao {
    @Insert
    fun insert(user:UserEntity)

    @Update
    fun update(user:UserEntity)

    @Delete
    fun delete(user:UserEntity)

    @Query("DELETE FROM users where username = :username")
    fun deleteQuery(username: String):Int //return Int jika mau tau brp row yg kehapus

    @Query("SELECT * FROM users")
    fun fetch():List<UserEntity>

    @Query("SELECT * FROM users where username = :username")
    fun get(username:String):UserEntity?
}