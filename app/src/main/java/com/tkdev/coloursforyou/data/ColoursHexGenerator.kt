package com.tkdev.coloursforyou.data

import com.tkdev.coloursforyou.data.model.Colour
import kotlin.random.Random

interface ColoursHexGenerator {
    fun generateHexColour(list: List<String>): List<Colour>
}

class ColoursHexGeneratorDefault : ColoursHexGenerator{
    override fun generateHexColour(list: List<String>): List<Colour> {

        val colourList = ArrayList<Colour>()
        list.forEach { colourList.add(Colour(it, randomizeColor())) }

        return colourList.toList()
    }

    private fun randomizeColor(): String {
        val hexVariables =
            listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        val builder = StringBuilder()
        builder.append("#")
        for (i in 0..5) {
            builder.append(hexVariables[Random.nextInt(0, 15)])
        }
        return builder.toString()
    }
}