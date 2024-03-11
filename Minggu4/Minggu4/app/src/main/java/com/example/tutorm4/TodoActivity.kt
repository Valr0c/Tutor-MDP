package com.example.tutorm4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorm4.adapters.TodoAdapter
import com.example.tutorm4.models.ToDo
import com.example.tutorm4.repositories.TodoRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

/**
 * Aktivitas di mana pengguna dapat berinteraksi dengan daftar tugas mereka.
 *
 * Dalam aktivitas ini, kita akan menampilkan tugas-tugas yang harus diselesaikan oleh pengguna
 * (semacam...). Kita akan menggunakan RecyclerView untuk demonstrasi ini.
 */
class TodoActivity : AppCompatActivity() {
    lateinit var todoHeader: TextView
    lateinit var todoAdapter: TodoAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var addActionButton: FloatingActionButton
    lateinit var changeLayoutButton: Button

    var showcaseMode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        todoHeader = findViewById(R.id.todoHeader)
        // Untuk mengambil data primitif yang telah dilempar oleh activity sebelumnya, kita dapat
        // menggunakan property bawaan dari "AppCompactActivity" bernama "intent" untuk
        // mendapatkannya. Property "intent" memiliki sebuah method "getStringExtra()" yang menerima
        // sebuah argumen yang ditujukan untuk mengidentifikasi data apa yang ingin diambil, seperti
        // sebuah alamat rumah. Dalam kasus kita, kita ingin mengambil "username".
        todoHeader.text = "${intent.getStringExtra("username")} To Do List"

        // Log adalah kelas yang berguna untuk debugging karena akan mencetak detail di panel
        // "Logcat". Untuk tujuan demonstrasi, kode di bawah ini akan mencetak log di panel "Logcat"
        // di Android Studio dengan tag "TASK LIST" dan dengan flag DEBUG dengan simbol "D" dan
        // mencetak panjang total task yang ada di objek TodoRepository.
        Log.d("TASK LIST", "Length: ${TodoRepository.todoList.size}")

        // Untuk menggunakan RecyclerView, kita membutuhkan 3 hal.
        // - Recycler view itu sendiri;
        // - Sebuah LayoutManager;
        // - Sebuah Adapter RecyclerView.
        //
        // Recycler view adalah yang akan menampilkan daftar item di UI. Layout Manager memberitahu
        // RecyclerView BAGAIMANA cara menampilkan item. Adaptor RecyclerView memegang dan
        // mengadaptasi data saat ini dalam aplikasi dengan apa yang ditampilkan di UI oleh
        // RecyclerView.
        recyclerView = findViewById(R.id.todoRecyclerView)
        setRecyclerSetting()

        addActionButton = findViewById(R.id.addActionButton)
        addActionButton.setOnClickListener {
            // Sisipkan instance kelas Task baru ke daftar repositori.
            TodoRepository.todoList.add(
                ToDo(
                    "Generated Task ${Random.nextInt(1, 1000)}",
                    Random.nextBoolean()
                )
            )

            Log.i("ITEM INSERTED", "New Task has been added!")

            // PENTING!!!
            // Ini adalah bentuk lain dari metode yang dapat dipanggil selain dari metode
            // "notifyDataSetChanged()". Fungsinya sama, yaitu untuk me-refresh tampilan
            // RecyclerView untuk memastikan bahwa data yang ditampilkan adalah yang terbaru. Namun,
            // method "notifyItemInserted" ini hanya merefresh bagian tertentu saja, yaitu item yang
            // baru disisipkan ke dalam daftar data. Hal ini membuatnya lebih cepat di-refresh dari
            // segi performa.
            todoAdapter.notifyItemInserted(TodoRepository.todoList.size-1)
        }

        // Change Layout Button ini akan digunakan untuk memungkinkan RecyclerView merubah layoutnya
        // saat aplikasi sedang dijalankan. Dalam kasus ini, kita hanya ingin berpindah dari 2 mode,
        // yaitu mode Linear, atau mode Grid.
        changeLayoutButton = findViewById(R.id.changeLayoutButton)
        changeLayoutButton.setOnClickListener {
            showcaseMode = (showcaseMode + 1) % 2
            setRecyclerSetting()
        }
    }

    /**
     * Saat activity lain sedang berjalan diatas activity ini, maka activity ini akan masuk ke mode
     * "paused". Hal itu berarti semua kegiatan dalam activity ini sedang dihentikan. Tetapi ketika
     * semua activity diatas activity ini sudah selesai atau ditutup, maka activity ini akan
     * memasuki mode resumed.
     *
     * Karena kita membuka activity baru untuk melakukan edit task, maka saat activity tersebut
     * ditutup, kita perlu melakukan "notify" ke "todoAdapter" untuk memastikan perubahan yang
     * dilakukan diperlihatkan oleh adapter.
     */
    override fun onResume() {
        super.onResume()
        todoAdapter.notifyDataSetChanged()
    }

    /**
     * Method ini mereset dan mengatur ulang adapter dan layout yang akan dipasang ke RecyclerView.
     *
     * Adapter untuk RecyclerView harus kita buat secara manual di class TodoAdapter. Untuk
     * kejelasan kelas ini dapat dilihat pada berkas "adapters/TodoAdapter".
     *
     * Layout Manager untuk RecyclerView terdapat 2 macam yang dapat digunakan, LinearLayoutManager
     * dan GridLayoutManager.
     */
    private fun setRecyclerSetting() {
        when (showcaseMode) {
            0 -> {
                // Dalam mode ini, kita akan menggunakan layout "todo_item_layout" Resource file.
                // Selain itu kita juga akan menggunakan LinearLayoutManager untuk membuat layout
                // terlihat seperti item list.
                todoAdapter = TodoAdapter(this, R.layout.todo_item_layout, TodoRepository.todoList)
                recyclerView.layoutManager = LinearLayoutManager(this)
            }
            else -> {
                // Dalam mode ini, kita akan menggunakan layout "todo_card_layout" Resource file.
                // Selain itu kita juga akan menggunakan GridLayoutManager untuk membuat layout
                // terlihat seperti sebuah grid.
                todoAdapter = TodoAdapter(this, R.layout.todo_card_layout, TodoRepository.todoList)
                recyclerView.layoutManager = GridLayoutManager(this, 2)
            }
        }

        recyclerView.adapter = todoAdapter
    }
}