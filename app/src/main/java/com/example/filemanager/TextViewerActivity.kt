package com.example.filemanager

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class TextViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_viewer)

        val filePath = intent.getStringExtra("filePath")
        val file = File(filePath)

        val textView: TextView = findViewById(R.id.textView)
        textView.text = file.readText()
    }
}