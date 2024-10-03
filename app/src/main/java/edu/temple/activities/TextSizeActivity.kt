package edu.temple.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TextSizeActivity.kt
class TextSizeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create array of integers that are multiples of 5
        val textSizes = Array(20) { (it + 1) * 5 }

        with(findViewById(R.id.textSizeSelectorRecyclerView) as RecyclerView) {
            adapter = TextSizeAdapter(textSizes) { selectedSize ->
                // Pass the selected size back to DisplayActivity
                val resultIntent = Intent().apply {
                    putExtra("selectedTextSize", selectedSize)
                }
                setResult(RESULT_OK, resultIntent)
                finish() // Close this activity and go back
            }
            layoutManager = LinearLayoutManager(this@TextSizeActivity)
        }
    }
}


/* Convert to RecyclerView.Adapter */
class TextSizeAdapter (private val textSizes: Array<Int>, private val callback: (Int)->Unit) : RecyclerView.Adapter<TextSizeAdapter.TextSizeViewHolder>() {

    inner class TextSizeViewHolder(val textView: TextView) : RecyclerView.ViewHolder (textView) {
        init {
            textView.setOnClickListener { callback(textSizes[adapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSizeViewHolder {
        return TextSizeViewHolder(TextView(parent.context).apply { setPadding(5, 20, 0, 20) })
    }

    override fun onBindViewHolder(holder: TextSizeViewHolder, position: Int) {
        holder.textView.apply {
            text = textSizes[position].toString()
            textSize = textSizes[position].toFloat()
        }
    }

    override fun getItemCount(): Int {
        return textSizes.size
    }

}








