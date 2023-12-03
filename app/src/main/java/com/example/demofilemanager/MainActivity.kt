package com.example.demofilemanager

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var videoPicker: ActivityResultLauncher<Intent>
    lateinit var fileManagerBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fileManagerBtn = findViewById(R.id.fileManagerBtn)

        fileManagerBtn.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = ("video/*")
            videoPicker.launch(intent)
        }

        videoPicker =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val selectedVideoUri: Uri? = result.data?.data
                    selectedVideoUri?.let {
                        val playIntent = Intent(Intent.ACTION_VIEW, it)
                        playIntent.setDataAndType(it, "video/*")
                        startActivity(playIntent)
                    }
                }
            }

    }
}