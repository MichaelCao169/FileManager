package com.example.filemanager

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity(), FileAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fileAdapter: FileAdapter
    private lateinit var currentDirectory: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        currentDirectory = Environment.getExternalStorageDirectory()
        listFiles(currentDirectory)
    }

    private fun listFiles(directory: File) {
        val files = directory.listFiles()
        if (files != null) {
            fileAdapter = FileAdapter(files.toList(), this)
            recyclerView.adapter = fileAdapter
        } else {
            Toast.makeText(this, "Unable to access directory", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(file: File) {
        if (file.isDirectory) {
            listFiles(file)
        } else if (file.isFile && file.extension == "txt") {
            val intent = Intent(this, TextViewerActivity::class.java)
            intent.putExtra("filePath", file.absolutePath)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Unsupported file type", Toast.LENGTH_SHORT).show()
        }
    }
}