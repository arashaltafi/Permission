package com.arash.altafi.permission.startActivityForResult2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arash.altafi.permission.R
import com.google.android.material.button.MaterialButton

class Activity_4 : AppCompatActivity() {

    private lateinit var btnBack : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)

        init()
    }

    private fun init() {
        bindViews()
        btnBack.setOnClickListener {
            val intent : Intent = Intent()
            intent.putExtra("name2" , "arash altafi test 2")
            setResult(Activity.RESULT_OK , intent)
            finish()
        }
    }

    private fun bindViews() {
        btnBack = findViewById(R.id.btn_back2)
    }

}