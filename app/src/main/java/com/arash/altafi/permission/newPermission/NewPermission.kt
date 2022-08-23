package com.arash.altafi.permission.newPermission

import android.Manifest.permission.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arash.altafi.permission.R
import com.arash.altafi.permission.utils.PermissionUtils
import com.google.android.material.button.MaterialButton

class NewPermission : AppCompatActivity() {

    private lateinit var btnCameraPermission: MaterialButton

    private val registerResult = PermissionUtils.register(this,
        object : PermissionUtils.PermissionListener {
            override fun observe(permissions: Map<String, Boolean>) {
                if (permissions[CAMERA] == true) {
                    Toast.makeText(this@NewPermission , "CAMERA" , Toast.LENGTH_SHORT).show()
                }
            }
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_permission)

        findView()
        init()
    }

    private fun init() {
        btnCameraPermission.setOnClickListener {
            if (!PermissionUtils.isGranted(this, CAMERA)) {
                PermissionUtils.requestPermission(this, registerResult, CAMERA)
            } else {
                Toast.makeText(this@NewPermission , "CAMERA" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun findView() {
        btnCameraPermission = findViewById(R.id.btn_camera_permission)
    }

}