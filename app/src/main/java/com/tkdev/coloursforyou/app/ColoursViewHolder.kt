package com.tkdev.coloursforyou.app

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tkdev.coloursforyou.data.model.Colour
import kotlinx.android.synthetic.main.list_colour.view.*

class ColoursViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var colourWord: TextView = itemView.colourWord
    private var colourImage: ImageView = itemView.colourImage
    private var colourValue: TextView = itemView.colourValue

    fun setColour(colour: Colour) {
        colourWord.text = colour.word
        colourImage.setBackgroundColor(Color.parseColor(colour.colour))
        colourValue.text = colour.colour
    }
}