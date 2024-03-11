package com.example.tutorm4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.example.tutorm4.databinding.ActivityEditToDoBinding
import com.example.tutorm4.repositories.TodoRepository

/**
 * Activity ini akan mengurus masalah mengubah data dari class Task.
 */
class EditToDoActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditToDoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Penjelasan tentang View Binding telah dijelaskan di MainActivity.
        binding = ActivityEditToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil index Task dari TodoRepository, yang diberikan oleh activity sebelumnya.
        val todoIndex = intent.getIntExtra("todoIndex", -1)
        if (todoIndex == -1) {
            Log.e("Edit ToDo", "ToDo index is not valid!")
            finish()
        }

        // Pasang secara default teks dari EditText sesuai task dari kelas Task yang diambil.
        binding.editToDoEditText.setText(TodoRepository.todoList[todoIndex].task)

        binding.saveButton.setOnClickListener {
            // Lakukan pengecekan bahwa editText tidak boleh kosong sebelum di save.
            if ((binding.editToDoEditText.text?.trim()?.isEmpty() ?: true) == true) {
                Toast.makeText(
                    this@EditToDoActivity,
                    "Deskripsi To Do tidak boleh kosong!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Perbarui deskripsi kelas Task dengan yang diinputkan user dan simpan ke repository.
            TodoRepository.todoList[todoIndex].task = binding.editToDoEditText.text.toString()

            Log.i("ToDo RecyclerView", "Task edit saved!")

            // Akhiri activity ini.
            finish()
        }

        // Batalkan perubahan Task bila tombol cancel ditekan.
        binding.cancelButton.setOnClickListener {
            finish()
        }
    }
}