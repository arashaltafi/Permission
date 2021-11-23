package com.arash.altafi.permission.camera

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import com.arash.altafi.permission.R
import com.arash.altafi.permission.utils.RunTimePermissionKotlin
import com.google.android.material.button.MaterialButton

class CameraPermissionWithClass : RunTimePermissionKotlin() {

    //region variables
    private lateinit var btnCamera: MaterialButton
    private lateinit var img : ImageView

    private val REQUEST_CAMERA_CODE : Int = 123
    private val PERMISSION_CAMERA_CODE : Int = 123
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camer_permission_with_class)

        init()

    }

    private fun init() {
        bindViews()

        btnCamera.setOnClickListener {
            super@CameraPermissionWithClass.requestAppPermissions(arrayOf(Manifest.permission.CAMERA), PERMISSION_CAMERA_CODE)
        }
    }

    private fun bindViews() {
        btnCamera = findViewById(R.id.btn_camera_class)
        img = findViewById(R.id.img_class)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAMERA_CODE && resultCode == -1) {
            val bitmap : Bitmap = data?.extras?.get("data") as Bitmap
            if (bitmap != null) {
                img.setImageBitmap(bitmap)
            }
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CAMERA_CODE)
    }

    override fun onPermissionsGranted(requestCode: Int) {
        if (requestCode == PERMISSION_CAMERA_CODE) {
            Toast.makeText(this, "مجوز تایید شد", Toast.LENGTH_SHORT).show()
            openCamera()
        }
    }

    override fun onPermissionsDeny(requestCode: Int) {
        Toast.makeText(this, "شما مجوز دسترسی را تایید نکرده اید", Toast.LENGTH_SHORT).show()
    }

}