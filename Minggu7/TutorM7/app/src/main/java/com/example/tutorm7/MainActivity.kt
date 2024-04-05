package com.example.tutorm7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Local Storage :D
Digunakan untuk menyimpan data layaknya sebuah database dalam aplikasi Android.
Data yang disimpan tidak akan hilang walaupun aplikasi ditutup
Kalo diuninstall ya ilang :D
Ada 3 hal yang harus diurus ketika mengimplementkan local storage:
1. AppDatabase
2. DAO
3. Entity Model

Sebelum itu, pastikan sudah nambahi keempat implementation ini di build.gradle dependencies
implementation "androidx.room:room-runtime:2.4.3"
kapt "androidx.room:room-compiler:2.4.3"
implementation "androidx.room:room-ktx:2.4.3"
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0'

Tambahkan ini pada plugin
id("kotlin-kapt")

===================================== DB DEBUG =====================================
Jika mau melihat isi dari DB, kalian bisa ikuti langkah berikut
1. Buka App Inspection pada bagian bawah (sebelah Logcat)
2. Pilih Database Inspector.
3. Pilih tabel mana yang mau dilihat
4. Centang Live Update jika mau melihat perubahan isi database secara langsung

 */

class MainActivity : AppCompatActivity() {

    private lateinit var txtName: TextView
    private lateinit var txtUsername: TextView
    private lateinit var txtPassword: TextView
    private lateinit var btnSave: Button
    private lateinit var rvUser: RecyclerView
    private lateinit var userAdapter: UserAdapter
    //Deklarasi variabel AppDatabase
    private lateinit var db: AppDatabase

    /*
    Deklarasi variabel Coroutine
    KotlinCoroutine merupakan variabel yang digunakan untuk mengeksekusi command
    di luar main threading, basically executing asynchronous command.
    Contohnya adalah pemanggilan query database.
    Knp harus di luar main threading?
    Karena kalo query diexecute di dalem main threading (UI thread) bakal nyebabin aplikasi android
    ngecrash
    Untuk tutor ini, kita pakai Thread-nya IO
     */
    private val coroutine = CoroutineScope(Dispatchers.IO)
    private lateinit var users: ArrayList<UserEntity>
    private var isInsertMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtName = findViewById(R.id.txtName)
        txtUsername = findViewById(R.id.txtUsername)
        txtPassword = findViewById(R.id.txtPassword)
        btnSave = findViewById(R.id.btnSave)
        rvUser = findViewById(R.id.rvUser)

        db = Room.databaseBuilder(baseContext, AppDatabase::class.java, "prakm7").build()
        users = ArrayList()
        rvUser.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        userAdapter = UserAdapter(users)
        rvUser.adapter = userAdapter


        /*
        Untuk melakukan query DB, command2 query ditaruh di dalam scope coroutine.launch{}
        Artinya, command2 di dalam scope ini akan diexecute di luar main thread
         */
        coroutine.launch {
            val tmpUser = db.userDao().fetch() //membaca database local di device user
            users.clear()
            users.addAll(tmpUser)
            /*
            Di dalam scope thread IO, kita juga bisa mengexecute command di main thread
            dengan membuat scope runOnUiThread{}
            Warnin
             */
            runOnUiThread {
                Toast.makeText(this@MainActivity, "Panjang list : ${users.size.toString()}", Toast.LENGTH_SHORT).show()
                userAdapter.notifyDataSetChanged()
            }
        }
        userAdapter.onEditClickListener = {
            isInsertMode = false
            btnSave.text = "UPDATE"
            txtName.text = it.name
            txtUsername.text = it.username
            txtPassword.text = it.password
        }
        userAdapter.onDeleteClickListener = {
            coroutine.launch {
                db.userDao().deleteQuery(it.username)
                refresh()
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "User Deleted", Toast.LENGTH_SHORT).show()
                    resetFields()
                }
            }
        }

        btnSave.text = "INSERT"

        btnSave.setOnClickListener {
            val name = txtName.text.toString()
            val username = txtUsername.text.toString()
            val password = txtPassword.text.toString()

            val user = UserEntity(
                name = name,
                username = username,
                password = password
            )

            coroutine.launch {
                if (isInsertMode) {
                    if (db.userDao().get(user.username) != null) {
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "Username not unique", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        db.userDao().insert(user)
                        refresh()
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "New user Inserted", Toast.LENGTH_SHORT).show()
                            resetFields()
                        }
                    }
                } else {
                    db.userDao().update(user)
                    refresh()
                    isInsertMode = true
                    runOnUiThread {
                        btnSave.text = "INSERT"
                        resetFields()
                        Toast.makeText(this@MainActivity, "Success update user", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
        btnSave.setOnLongClickListener{
            resetFields()
            Toast.makeText(this, "Field Reset", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }
    }

    private fun refresh() {
        users.clear()
        users.addAll(db.userDao().fetch().toMutableList())
        Log.i("USER", users.toString())
        runOnUiThread {
            userAdapter.notifyDataSetChanged()
        }
    }

    fun resetFields() {
        txtUsername.setText("")
        txtName.setText("")
        txtPassword.setText("")
    }

//    fun insert(user: UserEntity) {
//        /*
//        Untuk melakukan query DB, command2 query ditaruh di dalam scope coroutine.launch{}
//        Artinya, command2 di dalam scope ini akan diexecute di luar main thread
//         */
//        coroutine.launch {
//            if (db.userDao.get(user.username) != null) {
//                runOnUiThread {
//                    Toast.makeText(this@MainActivity, "Username not unique", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            } else {
//                db.userDao.insert(user)
//                refresh()
//                runOnUiThread {
//                    Toast.makeText(this@MainActivity, "Insert new user", Toast.LENGTH_SHORT).show()
//                    resetFields()
//                }
//            }
//        }
//    }

//    fun btnClick() {
//        val name = txtName.text.toString()
//        val username = txtUsername.text.toString()
//        val password = txtPassword.text.toString()
//
//        val user = UserEntity(
//            name = name,
//            username = username,
//            password = password
//        )
//
//        coroutine.launch {
//            if (isInsertMode) {
//                insert(user)
//            } else {
//                db.userDao.update(user)
//                refresh()
//                isInsertMode = true
//                runOnUiThread {
//                    btnSave.text = "ADD"
//                    resetFields()
//                    Toast.makeText(this@MainActivity, "Success update item", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//        }
//
//    }


//    suspend fun delete(user:UserEntity){
//        db.userDao.delete(user)
//        refresh()
//        runOnUiThread {
//            Toast.makeText(this@MainActivity,"Success delete user",Toast.LENGTH_SHORT).show()
//        }
//    }
}