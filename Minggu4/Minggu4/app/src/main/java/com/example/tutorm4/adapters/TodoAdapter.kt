package com.example.tutorm4.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorm4.EditToDoActivity
import com.example.tutorm4.R
import com.example.tutorm4.models.ToDo

/**
 * Kelas adaptor untuk RecyclerView yang akan mencantumkan semua tugas yang dimiliki pengguna.
 *
 * Mengimplementasikan kelas RecyclerView.Adapter dan menggunakan kelas ViewHolder khusus yang
 * terdapat di dalamnya Kelas ini sendiri disebut "TodoViewHolder".
 */
class TodoAdapter(
    val context: Context,
    val layout: Int,
    val todoList: ArrayList<ToDo>,
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    /**
     * Class dalam yang mewakili antarmuka setiap element khusus dalam tampilan RecyclerView.
     *
     * Setiap element dalam tampilan RecyclerView akan menggunakan class ini untuk menampilkannya
     * kepada pengguna, dan setiap interaktivitas item seperti onClickListeners sebaiknya
     * dideklarasikan di sini.
     */
    inner class TodoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val cardTextView: TextView?
        val statusCheckBox: CheckBox
        val deleteTodoButton: Button

        // Inisialisasi setelah instansi kelas TodoViewHolder telah dibuat.
        init {
            cardTextView = view.findViewById(R.id.cardTextView)
            statusCheckBox = view.findViewById(R.id.statusCheckBox)
            deleteTodoButton = view.findViewById(R.id.deleteTodoButton)
        }

        /**
         * Mengikat data yang diperlukan dan yang dapat dipanggil ke ViewHolder khusus ini.
         *
         * Dalam ViewHolder kustom ini, ada 2 hal yang dibutuhkan:
         * 1. Kelas Task itu sendiri;
         * 2. Fungsi hapus yang akan dipanggil ketika tombol hapus tampilan ViewHolder ditekan.
         *
         * Setelah argumen diberikan ke metode ini, atur atribut view sesuai dengan nilai kelas
         * Task, serta mengatur pendengar untuk memeriksa Task, dan saat menghapus tugas.
         *
         * Perlu diketahui, cara ini tidaklah wajib untuk diikuti karena kita dapat menetapkan
         * listeners dan property dari views yang ada dalam ViewHolder dalam method
         * "onBindViewHolder()". Cara ini hanya memberikan cara alternatif selayaknya kita membuat
         * sebuah activity, dimana kita medeklarasikan property dari views dan menetapkan listeners
         * dari dalam 1 class yang sama, tanpa campur tangan class parent.
         */
        fun bind(todo: ToDo, onDelete: (Int) -> Unit) {
            statusCheckBox.isChecked = todo.status

            if (cardTextView == null) {
                statusCheckBox.text = todo.task
            }
            else {
                cardTextView.text = todo.task
                statusCheckBox.text = if (todo.status) "Finished" else "Not Finished"
            }

            statusCheckBox.setOnClickListener(object : OnClickListener {
                override fun onClick(v: View?) {
                    Log.i("ToDo RecyclerView", "${todo.task}, checked!")
                    todo.status = statusCheckBox.isChecked

                    if (cardTextView != null) {
                        statusCheckBox.text = if (todo.status) "Finished" else "Not Finished"
                    }
                }
            })

            // Untuk melakukan edit task, maka kita tetapkan untuk melakukan Long Click, dimana
            // Pengguna harus menekan CheckBox, atau teks yang ada di sampingnya lebih dari setengah
            // detik, untuk membuka halaman perubahan.
            view.setOnLongClickListener {
                val editIntent = Intent(context, EditToDoActivity::class.java)
                editIntent.putExtra("todoIndex", adapterPosition)

                // Karena kita sedang berada dalam kode Adapter, kita tidak bisa langsung memanggil
                // "startActivity()" selayaknya dalam activity, oleh karena itu TodoAdapter ini
                // menerima sebuah parameter "context" dengan tipe data Context. Dengan begitu, kita
                // sekarang dapat memeanggil method "startActivity()" diluar dari sebuah activity.
                (context as Activity).startActivity(editIntent)

                return@setOnLongClickListener true
            }

            deleteTodoButton.setOnClickListener {
                Log.i("ToDo RecyclerView", "${todo.task}, deleted!")

                // Panggil fungsi "onDelete()" yang diberikan sebagai argumen dari parameter
                // "bind()". Dapat dilihat kita menggunakan sebuah property yang eksklusif untuk
                // class ViewHolder milik RecyclerView yaitu "adapterPosition". Property ini
                // memberi tahu class pada indeks keberapa ViewHolder ini sedang ditampung oleh
                // adapter RecyclerView.
                onDelete(this.adapterPosition)

                // PENTING!!!
                // Pastikan untuk memanggil method ini yang diberikan oleh kelas ViewHolder ketika
                // anda mencoba membuat perubahan apapun pada data.
                //
                // Dalam kasus ini, kita mencoba untuk menghapus sebuah elemen dari todoList, dan
                // oleh karena itu perlu memanggil method ini. Jika tidak, RecyclerView tidak akan
                // menampilkan data terbaru ke pengguna.
                //
                // Alternatif dari method ini adalah "notifyDataSetChanged()", tetapi method itu
                // diperuntukan untuk bila dataset berubah total, sehingga bila menggunakan itu akan
                // menghasilkan performa yang sub-optimal setiap kali ada perubahan data.
                notifyItemRemoved(this.adapterPosition)
            }
        }
    }

    /**
     * Apa yang harus dilakukan ketika Adaptor mulai membuat ViewHolder.
     *
     * Dalam kasus ini, kita ingin me-inflate layout Resource file yang akan ditampilkan oleh
     * ViewHolder ke UI saat ViewHolder dibuat.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        // Mengembang Resource layout file "todo_item_layout" dan memberikannya ke custom
        // ViewHolder. Pastikan argumen ke-3 diberi nilai false kalau tidak akan menghasilkan error.
        //
        // Disini kita juga menggunakan variable "layout" yang merupakan salah satu parameter dari
        // adapter ini. Hal ini bertujuan agar adapter ini dapat menggunakan berbagai Resource
        // layout file yang memang ditujukan untuk digunakan oleh adapter ini.
        //
        // Dua layout yang akan digunakan adalah:
        // 1. R.layout.todo_item_layout
        // 2. R.layout.todo_card_layout
        val view = LayoutInflater.from(parent.context)
             .inflate(layout, parent, false)

        return TodoViewHolder(view)
    }

    /**
     * Menyediakan cara bagi adaptor untuk dapat mengembalikan total item yang saat ini ditampungnya.
     */
    override fun getItemCount(): Int {
        return todoList.size
    }

    /**
     * Apa yang harus dilakukan setelah ViewHolder berhasil dibuat?
     *
     * Metode ini memberi tahu adaptor untuk mulai mengikat nilai yang dimiliki adaptor ini (Dalam
     * hal ini adalah variable todoList) ke ViewHolder.
     */
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        // Setelah membuat ViewHolder untuk item RecyclerView, ikat kelas tugas ke view holder
        // dengan memanggil fungsi custom view holder "bind()". Argumennya adalah diteruskan ke
        // dalam metode "bind ()" adalah kelas Task itu sendiri, dan sebuah fungsi untuk memanggil
        // ketika tombol hapus pada tata letak ditekan.
        holder.bind(todoList[position], fun (holderPosition: Int) {
            todoList.removeAt(holderPosition)
        })
    }
}