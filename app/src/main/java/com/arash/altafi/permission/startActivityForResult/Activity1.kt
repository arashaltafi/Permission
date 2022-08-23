package com.arash.altafi.permission.startActivityForResult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.arash.altafi.permission.R
import com.google.android.material.button.MaterialButton

class Activity1 : AppCompatActivity() {

    private lateinit var btnNext : MaterialButton
    private lateinit var txtName : TextView
    private val REQUEST_CODE : Int = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        init()
    }

    private fun init() {
        bindViews()
        btnNext.setOnClickListener {
            startActivityForResult(Intent(this, Activity2::class.java) , REQUEST_CODE)
        }
    }

    private fun bindViews() {
        btnNext = findViewById(R.id.btn_next)
        txtName = findViewById(R.id.text_name)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null)
        {
            txtName.text =data.getStringExtra("name")
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}