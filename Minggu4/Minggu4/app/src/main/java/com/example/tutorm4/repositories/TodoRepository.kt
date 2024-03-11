package com.example.tutorm4.repositories

import com.example.tutorm4.models.ToDo

/**
 * Sebuah objek yang menyimpan daftar Task yang disimpan dalam aplikasi.
 *
 * Karena repository ini merupakan sebuah object dan bukan class, maka kita dapat mengakses property
 * dari object ini tanpa membuat instansi terlebih dahulu, seperti contoh berikut:
 * ```
 * val firstElement = TodoRepository.todoList[0]
 * ```
 *
 * Object ini ditujukan untuk dapat menyimpan data secara global ke seluruh aplikasi, dimana dalam
 * kasus ini, data dari property "todoList" akan menyimpan Tasks yang dibuat oleh pengguna.
 */
object TodoRepository {
    val todoList = arrayListOf<ToDo>(
        ToDo("Clean the bathroom", false),
        ToDo("Dry the clothes", false),
        ToDo("Fry the chickens", true),
        ToDo("Make the bed", false)
    )
}