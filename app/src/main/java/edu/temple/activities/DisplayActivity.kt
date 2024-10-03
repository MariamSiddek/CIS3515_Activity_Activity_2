package edu.temple.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DisplayActivity : AppCompatActivity() {

    private lateinit var lyricsDisplayTextView: TextView
    private lateinit var textSizeSelectorButton: Button

    // Request code to identify the result coming from TextSizeActivity
    private val TEXT_SIZE_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        lyricsDisplayTextView = findViewById(R.id.lyricsDisplayTextView)
        textSizeSelectorButton = findViewById(R.id.textSizeSelectorButton)

        textSizeSelectorButton.setOnClickListener {
            // Launch TextSizeActivity for a result
            val intent = Intent(this, TextSizeActivity::class.java)
            startActivityForResult(intent, TEXT_SIZE_REQUEST_CODE)
        }
    }

    // Step 3: Get the result and set the text size
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TEXT_SIZE_REQUEST_CODE && resultCode == RESULT_OK) {
            // Get the selected text size
            val selectedTextSize = data?.getIntExtra("selectedTextSize", 16)
            // Set the lyrics text size
            lyricsDisplayTextView.textSize = selectedTextSize?.toFloat() ?: 16f
        }
    }
}