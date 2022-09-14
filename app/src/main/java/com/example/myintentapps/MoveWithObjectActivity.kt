package com.example.myintentapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveWithObjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Mengset dimana akan ditampilkan datanya, Berdasarkan Nama Layout.
        setContentView(R.layout.activity_move_with_object)

        //Inisialisasi, Nama Id yang ada pada Layout.
    val tvObject:TextView = findViewById(R.id.tv_object_received)

        //Variable yang berfungsi untuk membawa mengambil data dari DATACLASS
        //yang sudah di Parcelable <Person(Object DataClass)>
    val person = intent.getParcelableExtra<Person>(EXTRA_PERSON) as Person
    val text = "Name : ${person.name.toString()}, \nEmail : ${person.email}, \n age : ${person.age} \nLocation : ${person.city}"
        tvObject.text = text

    }

    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

}