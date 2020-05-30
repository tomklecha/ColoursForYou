package com.tkdev.coloursforyou.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkdev.coloursforyou.R
import com.tkdev.coloursforyou.data.model.Colour

class ColoursAdapter(private val list: List<Colour>): RecyclerView.Adapter<ColoursViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColoursViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ColoursViewHolder(inflater.inflate(R.layout.list_colour, null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ColoursViewHolder, position: Int) {
        val colourItem = list[position]
        holder.setColour(colourItem)
    }
}