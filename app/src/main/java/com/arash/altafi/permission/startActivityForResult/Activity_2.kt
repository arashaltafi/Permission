package com.arash.altafi.permission.startActivityForResult

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arash.altafi.permission.R
import com.google.android.material.button.MaterialButton

class Activity_2 : AppCompatActivity() {

    private lateinit var btnBack : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        init()
    }

    private fun init() {
        bindViews()
        btnBack.setOnClickListener {
            val intent : Intent = Intent()
            intent.putExtra("name" , "arash altafi test")
            setResult(Activity.RESULT_OK , intent)
            finish()
        }
    }

    private fun bindViews() {
        btnBack = findViewById(R.id.btn_back)
    }

}