package com.arash.altafi.permission.utils

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

abstract class RunTimePermissionKotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    open override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions!!, grantResults)
        var permissionCheck = PackageManager.PERMISSION_GRANTED
        for (permission in grantResults) {
            permissionCheck = permissionCheck + permission
        }
        if (grantResults.size > 0 && permissionCheck == PackageManager.PERMISSION_GRANTED) {
            onPermissionsGranted(requestCode)
        } else {
            onPermissionsDeny(requestCode)
        }
    }

    open fun requestAppPermissions(requestedPermissions: Array<String?>, requestCode: Int) {
        var permissionCheck = PackageManager.PERMISSION_GRANTED
        var shouldShowRequestPermissionRationale = false
        for (permission in requestedPermissions) {
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(this, permission!!)
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale || ActivityCompat.shouldShowRequestPermissionRationale(this, permission!!)
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, requestedPermissions, requestCode)
        } else {
            onPermissionsGranted(requestCode)
        }
    }

    abstract fun onPermissionsGranted(requestCode: Int)
    abstract fun onPermissionsDeny(requestCode: Int)

}