package com.arash.altafi.permission.file

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.arash.altafi.permission.R
import com.google.android.material.button.MaterialButton
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder

class FilePermission : AppCompatActivity() {

    //region Variables
    private lateinit var edt_file_name : EditText
    private lateinit var edt_file_content : EditText
    private lateinit var btn_save : MaterialButton
    private lateinit var btn_load : MaterialButton
    //endregion

    //region onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_permission)

        init()
    }
    //endregion

    //region init
    private fun init() {
        bindViews()

        btn_save.setOnClickListener {
            try {
                val name = edt_file_name.text.toString()
                val content = edt_file_content.text.toString()
                val fileOutputStream : FileOutputStream = openFileOutput(name , MODE_PRIVATE)
                fileOutputStream.write(content.toByteArray())
                Toast.makeText(this , "فایل با موفقیت ذخیره شد" , Toast.LENGTH_SHORT).show()
                edt_file_name.text.clear()
                edt_file_content.text.clear()
            }
            catch (e:Exception) {
                Toast.makeText(this , e.message , Toast.LENGTH_SHORT).show()
            }
        }

        btn_load.setOnClickListener {
            try {
                val name = edt_file_name.text.toString()
                val fileInputStream : FileInputStream = openFileInput(name)
                val inputStreamReader : InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader : BufferedReader = BufferedReader(inputStreamReader)
                var txt : String = bufferedReader.readText()
                edt_file_content.setText(txt)
                Toast.makeText(this , "فایل نمایش داده شد" , Toast.LENGTH_SHORT).show()
            }
            catch (e:Exception) {
                Toast.makeText(this , e.message , Toast.LENGTH_SHORT).show()
            }
        }
    }
    //endregion

    //region bindViews
    private fun bindViews() {
        btn_save = findViewById(R.id.btn_save)
        btn_load = findViewById(R.id.btn_load)
        edt_file_content = findViewById(R.id.file_content)
        edt_file_name = findViewById(R.id.file_name)
    }
    //endregion

}