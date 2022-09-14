package com.example.myintentapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup

class MoveForResultActivity : AppCompatActivity(), View.OnClickListener {

    //Variable yang di Inisialisasi, type layout yang ada di XML.
    //Tujuan pembuatan Lateinit Diluar Class, Karna Agar dapat di panggil di semua Class Yang ada. (Memudahkan Inherite/Penurunan Object)
    private lateinit var rgNumber: RadioGroup
    private lateinit var btnChoose: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)

        rgNumber = findViewById(R.id.rg_number)
        btnChoose = findViewById(R.id.btn_choose)

        btnChoose.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_choose) {
            //karna tipe data yang di bawa berupa looping, Maka Digunakan If/when, Dimana ketika salah satu dipilih maka akan dijalankan programnya
            if (rgNumber.checkedRadioButtonId > 0) {
                //variable Default di set 0
                var value = 0
                //fungsi when, Ketika Button Check maka akan mengembalikan Nilai int
                when (rgNumber.checkedRadioButtonId){
                    //Nama id dalam Button memiliki Value 50
                    R.id.rb_50 -> value = 50
                    //Nama id dalam Button memiliki Value 100, dst
                    R.id.rb_100 -> value = 100
                    R.id.rb_150 -> value = 150
                    R.id.rb_200 -> value = 200
                }
                //Intent sendiri sudah dapat dipanggil tanpa harus berpindah Activity,
                //seperti contoh Dibawah ini.
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_SELECTED_VALUE, value)
                setResult(RESULT_CODE, resultIntent)
                finish()
            }
        }
    }

    companion object{
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
        const val RESULT_CODE = 110
    }
}