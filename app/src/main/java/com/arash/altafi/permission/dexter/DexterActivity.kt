package com.arash.altafi.permission.dexter

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arash.altafi.permission.R
import com.google.android.material.button.MaterialButton
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class DexterActivity : AppCompatActivity() {

    private lateinit var btnPermissionDexter: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dexter)

        findView()
        init()
    }

    private fun init() {
        btnPermissionDexter.setOnClickListener {
            Dexter.withContext(this)
                .withPermissions(arrayListOf(Manifest.permission.CAMERA))
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        // this method is called when all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(this@DexterActivity, "camera", Toast.LENGTH_SHORT).show()
                        }
                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied)
                            Toast.makeText(this@DexterActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                        token: PermissionToken?
                    ) {
                        token?.continuePermissionRequest()
                    }
                }).check()
        }
    }

    private fun findView() {
        btnPermissionDexter = findViewById(R.id.btn_permission_dexter)
    }

}