package edu.temple.activity4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val textSizeAdapter = findViewById<TextView>(R.id.textView)
        textSizeAdapter.textSize = intent.getFloatExtra("size", 20f)

    }
}