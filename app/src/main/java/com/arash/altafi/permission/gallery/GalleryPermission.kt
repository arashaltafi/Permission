package com.arash.altafi.permission.gallery

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import com.arash.altafi.permission.R
import com.google.android.material.button.MaterialButton
import java.util.jar.Manifest

class GalleryPermission : AppCompatActivity() {

    //region variables
    private lateinit var imgGallery : ImageView
    private lateinit var btnGallery : MaterialButton

    private val MY_GALLERY_REQUEST_CODE : Int = 85
    private val MY_GALLERY_PERMISSION_CODE : Int = 86
    //endregion

    //region onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_permission)

        init()
    }
    //endregion

    //region init
    private fun init() {
        bindViews()

        btnGallery.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(Array(1) { android.Manifest.permission.READ_EXTERNAL_STORAGE } , MY_GALLERY_PERMISSION_CODE)
                } else {
                    openGallery()
                }
            } else {
                openGallery()
            }
        }
    }
    //endregion

    //region onActivityResult
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_GALLERY_REQUEST_CODE && resultCode == -1) {
            val uri : Uri = data?.data!!
            if (uri != null) {
                imgGallery.setImageURI(uri)
            }
        }
    }
    //endregion

    //region onRequestPermissionsResult
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_GALLERY_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this , "OK" , Toast.LENGTH_SHORT).show()
                openGallery()
            }
            else
            {
                Toast.makeText(this , "NOK" , Toast.LENGTH_SHORT).show()
            }
        }
    }
    //endregion

    //region openGallery
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK , MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent , MY_GALLERY_REQUEST_CODE)
    }
    //endregion

    //region bindViews
    private fun bindViews() {
        imgGallery = findViewById(R.id.img_gallery)
        btnGallery = findViewById(R.id.btn_gallery)
    }
    //endregion

}