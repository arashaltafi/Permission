package com.arash.altafi.permission.newPermission

import android.Manifest.permission.*
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import com.arash.altafi.permission.R
import com.arash.altafi.permission.utils.PermissionUtils
import com.google.android.material.button.MaterialButton

class NewPermission : AppCompatActivity() {

    private lateinit var btnCameraPermission: MaterialButton
    private lateinit var btnContactPermission: MaterialButton

    private val registerContactResult = PermissionUtils.register(
        this,
        object : PermissionUtils.PermissionListener {
            override fun observe(permissions: Map<String, Boolean>) {
                if (permissions[READ_CONTACTS] == true) {
                    Toast.makeText(
                        this@NewPermission,
                        "permission contact is granted",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@NewPermission,
                        "permission contact is not granted",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    private val registerCameraResult = PermissionUtils.register(
        this,
        object : PermissionUtils.PermissionListener {
            override fun observe(permissions: Map<String, Boolean>) {
                if (permissions[CAMERA] == true) {
                    Toast.makeText(
                        this@NewPermission,
                        "permission camera is granted",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@NewPermission,
                        "permission camera is not granted",
                        Toast.LENGTH_SHORT
                    ).show()
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!shouldShowRequestPermissionRationale(CAMERA)) {
                        PermissionUtils.requestPermission(this, registerCameraResult, CAMERA)
                    } else {
                        intentToSetting()
                    }
                } else {
                    PermissionUtils.requestPermission(this, registerCameraResult, CAMERA)
                }
            } else {
                Toast.makeText(
                    this@NewPermission,
                    "permission CAMERA is ok",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnContactPermission.setOnClickListener {
            if (!PermissionUtils.isGranted(this, READ_CONTACTS)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!shouldShowRequestPermissionRationale(READ_CONTACTS)) {
                        PermissionUtils.requestPermission(this, registerContactResult, READ_CONTACTS)
                    } else {
                        intentToSetting()
                    }
                } else {
                    PermissionUtils.requestPermission(this, registerContactResult, READ_CONTACTS)
                }
            } else {
                Toast.makeText(
                    this,
                    "permission CONTACTS is ok",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun intentToSetting() {
        startActivity(
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", packageName, null)
            )
        )
    }

    private fun findView() {
        btnCameraPermission = findViewById(R.id.btn_camera_permission)
        btnContactPermission = findViewById(R.id.btn_contact_permission)
    }

}