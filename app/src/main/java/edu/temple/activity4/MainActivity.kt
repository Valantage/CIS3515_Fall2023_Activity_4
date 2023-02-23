package edu.temple.activity4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var textSizeSelector: RecyclerView
    lateinit var textSizeDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textSizeSelector = findViewById(R.id.textSizeSelectorRecyclerView)
        textSizeDisplay = findViewById(R.id.textSizeDisplayTextView)
        val launchIntent = Intent(this, MainActivity2::class.java)
        //some cool stuff
        //val someFunc : (Int, Int) -> Unit = { x: Int, y: Int ->textSizeDisplay.text = (x+y).toString()}
        //someFunc(4,6)
        //aRandomClass().myLocalFunction("Val", 21, someFunc)

        // you can use this if you want to pass it in the textSizeAdapter instead of doing it the way below
        //val callBack = {textSize: Float -> textSizeDisplay.textSize = textSize}

        // Trying to create array of integers that are multiples of 5
        // Verify correctness by examining array values.
        val textSizes = Array(20){(it + 1) * 5}
        Log.d("MainActivity", "Array made")

        textSizeSelector.adapter = TextSizeAdapter(textSizes){
            textSizeDisplay.textSize = it
            launchIntent.putExtra("size",it)
            this.startActivity(launchIntent)
        }
        textSizeSelector.layoutManager = LinearLayoutManager(this)

    }
}

/* Convert to RecyclerView.Adapter */
class TextSizeAdapter(_textSizes : Array<Int>, _callBack : (Float) -> Unit) : RecyclerView.Adapter<TextSizeAdapter.TextSizeViewHolder>() {

    val textSizes = _textSizes
    val callBack = _callBack

    inner class TextSizeViewHolder(view: TextView) : RecyclerView.ViewHolder(view) {
        val textView = view

        init{
            textView.setOnClickListener{
                callBack(textSizes[adapterPosition].toFloat())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSizeViewHolder {

        return TextSizeViewHolder(TextView(parent.context).apply { setPadding(5, 20, 0, 20) })
    }

    override fun onBindViewHolder(holder: TextSizeViewHolder, position: Int) {
        holder.textView.text = textSizes[position].toString()
        holder.textView.textSize = textSizes[position].toFloat()

    }

    override fun getItemCount(): Int {
        return textSizes.size
    }

}