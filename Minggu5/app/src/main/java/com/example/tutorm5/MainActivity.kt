package com.example.tutorm5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {
    /**
     * Materi minggu ini adalah Fragment and Navigation
     * Ada 3 hal yang akan dipelajari, yaitu Fragment, Menu, dan Navigation
     *
     * Fragment adalah bagian dari activity yang mewakili bagian interface (tampilan) dalam sebuah activity
     * Sebuah activity juga dapat menampilkan dua atau lebih fragment di saat yang sama,
     * yang pasti Fragment wajib berada di dalam activity
     * Sebuah fragment dibentuk dari layout XML dan class seperti activity pada umumnya
     * Untuk membuat fragment kosong, klik kanan di project > new > Fragment > Fragment (Blank)
     *
     * Menu adalah komponen UI yang sering ditemui pada aplikasi android,
     *  dan dapat digunakan untuk melakukan navigasi maupun melakukan aksi terhadap sesuatu
     * Ada 3 jenis menu, yaitu option, popup, dan context menu, namun yang akan digunakan di sini adalah option menu
     * Option menu biasanya sering ditampilkan pada sisi kanan atas dengan icon 3 titik
     * Contoh option menu https://images.ctfassets.net/piwi0eufbb2g/2EB52MnjRQMmREarKw0hf/d9c313fcc6a1d11a9f769e774b554689/Menu_in_Android_-_ss1.png
     * Untuk membuat menu, klik kanan pada res > New > Android Resource File
     * beri nama resource nya (usahakan namanya dapat mendeskripsikan nama menu dengan jelas), lalu ganti resource type menjadi menu
     * Untuk mengisi menu, dapat mendrag menu item, atau diisi lewat code xml
     *
     * Sebelum menggunakan menu, silahkan pergi ke folder res>values>themes>themes.xml
     * Hapuslah .NoActionBar, ini agar dapat memunculkan bar paling atas pada aplikasi
     *
     *
     * Navigation yang akan kita bahas di sini adalah bagaimana kita mengatur perpindahan fragment
     * Kita akan membuat navigation component untuk membuat alur navigasi aplikasi
     * Terdapat 3 komponen dalam navigation component (baca di buku praktikum untuk lebih jelasnya)
     * - Nav Graph (kumpulan destination fragment / activity dalam aplikasi)
     * - Nav Host (container yg menampilkan destinasi dari nav graph)
     * - Nav Controller (untuk melakukan aksi navigasi antar destinasi)
     * Jangan lupa tambah dependency navigation (cth nav version bisa pakai 2.7.7)
     *     implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
     *     implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
     * Untuk membuat nav graph
     * res > new > android resource file
     * resource type diganti menjadi navigation
     *
     * Untuk memberikan arguments pada salah satu fragment,
     *  - pilih fragment tersebut di navgraph
     *  - pada panel attributes, tambahkan argument baru
     *  - bisa diberikan nullable dan default value untuk argument
     */

    lateinit var container: FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container = findViewById(R.id.fragment_container)
    }

    /**
     * Ada 2 method yang harus dioverride untuk memunculkan option menu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menu ini digunakan untuk memilih menu mana yang akan dilekatkan pada activity
        menuInflater.inflate(R.menu.nav_menu, menu)
        /**
         * menuInflater: Objek untuk memuat file menu XML
         * R.menu.nav_menu: Resource ID file menu XML nav_menu
         * menu: Objek menu yang diisi dengan item-item dari file menu XML
         */
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //method ini untuk melakukan handling apabila ada item menu yang dipilih
        when(item.itemId){
            R.id.menu_home->{
                //ini untuk aksi mengganti fragment tanpa mengirimkan arguments
                container.getFragment<Fragment>().findNavController().navigate(R.id.action_global_homeFragment)
            }
            else->{
                container.getFragment<Fragment>().findNavController().navigate(R.id.action_global_createFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}