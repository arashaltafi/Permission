package com.arash.altafi.permission.seismic

import android.content.Context
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arash.altafi.permission.R
import com.squareup.seismic.ShakeDetector

class SeismicActivity : AppCompatActivity() {

    private lateinit var sensorManager: SensorManager
    private lateinit var shakeDetector: ShakeDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seismic)

        init()
    }

    private fun init() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        shakeDetector = ShakeDetector {
            Toast.makeText(this , "!!! SHAKE !!!" , Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        shakeDetector.start(sensorManager)
    }

    override fun onPause() {
        super.onPause()
        shakeDetector.stop()
    }

}