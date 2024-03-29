package com.arash.altafi.permission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arash.altafi.permission.camera.CameraPermission
import com.arash.altafi.permission.camera.CameraPermissionWithClass
import com.arash.altafi.permission.dexter.DexterActivity
import com.arash.altafi.permission.file.FilePermission
import com.arash.altafi.permission.filePermission.FilePermissionActivity
import com.arash.altafi.permission.gallery.GalleryPermission
import com.arash.altafi.permission.internet.CheckInternet
import com.arash.altafi.permission.newPermission.NewPermission
import com.arash.altafi.permission.pickFromDevice.CaptureAndPickImage
import com.arash.altafi.permission.startActivityForResult.Activity1
import com.arash.altafi.permission.startActivityForResult2.Activity3
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    //region variables
    private lateinit var btnNewPermission: MaterialButton
    private lateinit var btnFilePermission: MaterialButton
    private lateinit var btnPermissionDexter: MaterialButton
    private lateinit var btnCamera: MaterialButton
    private lateinit var btnGallery: MaterialButton
    private lateinit var btnFile: MaterialButton
    private lateinit var btnInternet: MaterialButton
    private lateinit var btnForResult: MaterialButton
    private lateinit var btnForResult2: MaterialButton
    private lateinit var btnPickFromDevice: MaterialButton
    private lateinit var btnPermissionClass: MaterialButton
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        bindViews()

        btnNewPermission.setOnClickListener {
            startActivity(Intent(this , NewPermission::class.java))
        }

        btnFilePermission.setOnClickListener {
            startActivity(Intent(this , FilePermissionActivity::class.java))
        }

        btnPermissionDexter.setOnClickListener {
            startActivity(Intent(this , DexterActivity::class.java))
        }

        btnCamera.setOnClickListener {
            startActivity(Intent(this , CameraPermission::class.java))
        }

        btnGallery.setOnClickListener {
            startActivity(Intent(this , GalleryPermission::class.java))
        }

        btnFile.setOnClickListener {
            startActivity(Intent(this , FilePermission::class.java))
        }

        btnInternet.setOnClickListener {
            startActivity(Intent(this , CheckInternet::class.java))
        }

        btnPermissionClass.setOnClickListener {
            startActivity(Intent(this , CameraPermissionWithClass::class.java))
        }

        btnForResult.setOnClickListener {
            startActivity(Intent(this , Activity1::class.java))
        }

        btnForResult2.setOnClickListener {
            startActivity(Intent(this , Activity3::class.java))
        }

        btnPickFromDevice.setOnClickListener {
            startActivity(Intent(this , CaptureAndPickImage::class.java))
        }
    }

    private fun bindViews() {
        btnNewPermission = findViewById(R.id.btn_permission_new_main)
        btnFilePermission = findViewById(R.id.btn_file_permission_new)
        btnPermissionDexter = findViewById(R.id.btn_permission_dexter_main)
        btnCamera = findViewById(R.id.btn_camera_main)
        btnGallery = findViewById(R.id.btn_gallery_main)
        btnFile = findViewById(R.id.btn_file_main)
        btnInternet = findViewById(R.id.btn_internet_main)
        btnPermissionClass = findViewById(R.id.btn_camera_class_main)
        btnForResult = findViewById(R.id.btn_for_result_main)
        btnForResult2 = findViewById(R.id.btn_for_result_main2)
        btnPickFromDevice = findViewById(R.id.btn_pick_from_device)
    }

}