package com.arash.altafi.permission.startActivityForResult2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.arash.altafi.permission.R
import com.google.android.material.button.MaterialButton

class Activity3 : AppCompatActivity() {

    private lateinit var btnNext : MaterialButton
    private lateinit var txtName : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        bindViews()
        init()
    }

    private fun init() {
        val resultActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                txtName.text = data!!.getStringExtra("name2")
            }
        }

        btnNext.setOnClickListener {
            val intent = Intent(this , Activity4::class.java)
            resultActivity.launch(intent)
        }
    }

    private fun bindViews() {
        btnNext = findViewById(R.id.btn_next2)
        txtName = findViewById(R.id.text_name2)
    }

}