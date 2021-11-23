package com.arash.altafi.permission.camera

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import com.arash.altafi.permission.R
import com.google.android.material.button.MaterialButton

class CameraPermission : AppCompatActivity() {

    //region variables
    private lateinit var btnCamera: MaterialButton
    private lateinit var img : ImageView

    private val MY_CAMERA_REQUEST_CODE : Int = 100
    private val MY_CAMERA_PERMISSION_CODE : Int = 200
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camer_permission)

        init()
    }

    private fun init() {
        bindViews()

        btnCamera.setOnClickListener {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(Array(1) { android.Manifest.permission.CAMERA } , MY_CAMERA_PERMISSION_CODE)
                }
                else
                {
                    openCamera()
                }
            }
            else
            {
                openCamera()
            }
        }

    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, MY_CAMERA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_CAMERA_REQUEST_CODE && resultCode == -1) {
            val bitmap : Bitmap = data?.extras?.get("data") as Bitmap
            if (bitmap != null) {
                img.setImageBitmap(bitmap)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this , "OK" , Toast.LENGTH_SHORT).show()
                openCamera()
            }
            else
            {
                Toast.makeText(this , "NOK" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bindViews() {
        btnCamera = findViewById(R.id.btn_camera)
        img = findViewById(R.id.img)
    }

}