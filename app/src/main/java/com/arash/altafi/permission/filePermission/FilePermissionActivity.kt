package com.arash.altafi.permission.filePermission

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import com.arash.altafi.permission.R
import com.google.android.material.button.MaterialButton

class FilePermissionActivity : AppCompatActivity() {

    private lateinit var btnFilePermission: MaterialButton

    private val registerStorageResult = PermissionUtils.register(
        this,
        object : PermissionUtils.PermissionListener {
            override fun observe(permissions: Map<String, Boolean>) {
                if (permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true) {
                    Toast.makeText(
                        this@FilePermissionActivity,
                        "permission storage is granted",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@FilePermissionActivity,
                        "permission storage is not granted",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    private val registerStorageResultAndroid13 = PermissionUtils.register(
        this,
        object : PermissionUtils.PermissionListener {
            override fun observe(permissions: Map<String, Boolean>) {
                if (
                    permissions[Manifest.permission.READ_MEDIA_IMAGES] == true &&
                    permissions[Manifest.permission.READ_MEDIA_VIDEO] == true &&
                    permissions[Manifest.permission.READ_MEDIA_AUDIO] == true
                ) {
                    Toast.makeText(
                        this@FilePermissionActivity,
                        "permission storage is granted",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@FilePermissionActivity,
                        "permission storage is not granted",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_permission2)

        init()
    }

    private fun init() {
        btnFilePermission = findViewById(R.id.btn_get_file_permission)

        btnFilePermission.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (
                    !PermissionUtils.isGranted(
                        this,
                        Manifest.permission.READ_MEDIA_IMAGES,
                        Manifest.permission.READ_MEDIA_VIDEO,
                        Manifest.permission.READ_MEDIA_AUDIO
                    )
                ) {
                    PermissionUtils.requestPermission(
                        this, registerStorageResultAndroid13,
                        Manifest.permission.READ_MEDIA_IMAGES,
                        Manifest.permission.READ_MEDIA_VIDEO,
                        Manifest.permission.READ_MEDIA_AUDIO
                    )
                } else {
                    afterPermissionGranted()
                }
            } else {
                if (!PermissionUtils.isGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            PermissionUtils.requestPermission(
                                this, registerStorageResult,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            )
                        } else {
                            intentToSetting()
                        }
                    } else {
                        PermissionUtils.requestPermission(
                            this, registerStorageResult,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                    }
                } else {
                    afterPermissionGranted()
                }
            }
        }
    }

    private fun afterPermissionGranted() {
        Toast.makeText(this , "file permission is granted" , Toast.LENGTH_SHORT).show()
    }

    private fun intentToSetting() {
        startActivity(
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", packageName, null)
            )
        )
    }

}