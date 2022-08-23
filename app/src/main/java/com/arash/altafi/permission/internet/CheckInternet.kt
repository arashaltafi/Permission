package com.arash.altafi.permission.internet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arash.altafi.permission.R
import com.arash.altafi.permission.utils.InternetConnection

class CheckInternet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_internet)

        if (InternetConnection.isInternetAvailable(this)) {
            Toast.makeText(this, "connect", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "not connect!!!", Toast.LENGTH_SHORT).show()
        }
    }
}