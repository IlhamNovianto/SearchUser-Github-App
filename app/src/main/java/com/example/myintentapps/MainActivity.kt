package com.example.myintentapps

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

/*
Untuk Aplikasi ini, menerapkan Intent atau perpindahan data
ke Activity/Fragment, atau ke Aplikasi Lainnya dengan Membawa data.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvResult: TextView

    /*registerForActivityResult dengan parameter ActivityResultContract berupa ActivityResultContract.
    Hal ini karena kita akan mendapatkan nilai kembalian setelah memanggil Activity baru.
    Perlu diketahui bahwa kita juga bisa mendapatkan nilai kembalian dari selain Activity */
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result -> if (result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null) {
            val selectedValue = result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
            tvResult.text = "Hasil : $selectedValue"
        }
    }

    // Fungsi onCreate Dibuat untuk menyambungkan Layout Dengan Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Diset dengan Activity_main.XML
        setContentView(R.layout.activity_main)

        //Inisialisasi Button untuk memindahkan Activity
        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveWithDataActivity: Button = findViewById(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)

        val btnMoveWithObject: Button = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObject.setOnClickListener(this)

        val btnDialPhone: Button = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

        val btnMoveForResult:Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)
        tvResult = findViewById(R.id.tv_result)

    }

    //Intent Method, jika mengKlik Button(Behavior), Mengirimkan Data.
    //atau untuk mengerjakan aktifitas apa ketika button di klik.
    override fun onClick(v: View?) {
        /*
        Contoh Explicit Intent,
        menjalankan komponen dari dalam sebuah aplikasi.
        Explicit intent bekerja dengan menggunakan nama
        kelas yang Dituju.  Berpindah Activity ke Move.Activity
         */
        when (v?.id) {
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }

            /*
        Berpindah Activity, dengan Membawa data yang ada di MainActivity,
        pada Activity Target yang dituju, diberikan
        Inisialisasi Value yang akan di terima berupa,
        Companion Object
         */
            R.id.btn_move_activity_data -> {
                //Membuat Variable, yang berisi Pengirim data dan Penerima Data
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                //Metode Untuk mengirimkan data, dengan memanggil Companion Object yang sudah dibuat di Activity Penerima
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Dicoding Academy Boy")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5)
                startActivity(moveWithDataIntent)
            }

            /*
            Cara yang sama seperti Diatas,
            Namun data yang dibawa berupa Object,
            yang memiliki data.
            Namun Bedanya, Jika Object harus membuat
            Data Class.kt terlebih dahulu unutk mengirimkan data.
            Lalu data tersebut di Parcelabel, dan di panggil berdasarkan List
            pada Activity Tujuan.
             */
            R.id.btn_move_activity_object -> {
                //Variable di buat untuk Inisialisasi Value Object Berupa data
                // "Person" adalah sebuah DataClass, Yang di Insialisasikan pada Variable "person"
                val person = Person(
                    "Dicoding Academy",
                    5,
                    "academy@dicoding.com",
                    "Bandung"
                )

                //Disinilah fungsi, Pemindahan Object menuju Activity Penerima Data.
                val moveWithObjectIntent =
                    Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity((moveWithObjectIntent))
            }

            /*
            Contoh Penerapan Implicit Intent,
            untuk menjalankan fitur/fungsi dari komponen aplikasi lain
            Contoh, Dial, Photo, Google Crome, dll.
             */
            R.id.btn_dial_number -> {
                val phoneNumber = "081296045191"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            //Konsepnya, Ketika Klik di MainActivity, akan mengarahkan ke moveForResultActivity
            R.id.btn_move_for_result -> {
                val moveForResultIntent =
                    Intent(this@MainActivity, MoveForResultActivity::class.java)
                //Setelah menCheck Button yang ada di moveForResultActivity, Akan mengirimkan data tadi ka Main
                //Mengantikan Text_View Valuenya.
                resultLauncher.launch(moveForResultIntent)
            }
        }
    }
}
