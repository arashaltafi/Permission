package com.arash.altafi.permission.newPermission

import android.Manifest.permission.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arash.altafi.permission.R
import com.arash.altafi.permission.utils.PermissionUtils
import com.google.android.material.button.MaterialButton
import java.util.jar.Manifest

class NewPermission : AppCompatActivity() {

    private lateinit var btnCameraPermission: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_permission)

        findView()
        init()
    }

    private fun init() {

        PermissionUtils.register(this, object : PermissionUtils.PermissionListener {
            override fun observe(permissions: Map<String, Boolean>) {
                permissions.forEach {
                    when(it.key) {
                        (CAMERA) -> {
                            Toast.makeText(this@NewPermission , "CAMERA" , Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })

        btnCameraPermission.setOnClickListener {
            if (!PermissionUtils.isGranted(this, CAMERA)) {
                PermissionUtils.requestPermission(this, arrayOf(CAMERA))
            } else {
                Toast.makeText(this@NewPermission , "CAMERA" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun findView() {
        btnCameraPermission = findViewById(R.id.btn_camera_permission)
    }

}