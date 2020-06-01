package com.tkdev.coloursforyou.data

import com.tkdev.coloursforyou.data.model.Colour
import kotlin.random.Random

interface ColoursHexGenerator {
    fun generateHexColour(list: List<String>): List<Colour>
}

class ColoursHexGeneratorDefault : ColoursHexGenerator {
    override fun generateHexColour(list: List<String>): List<Colour> {

        val colourList = ArrayList<Colour>()
        list.forEach { colourList.add(Colour(it, randomizeColor())) }

        return colourList.toList()
    }

    private fun randomizeColor(): String {

        val red = Random.nextInt(0, 255)
        val green = Random.nextInt(0, 255)
        val blue = Random.nextInt(0, 255)

        val hexVariables =
            listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")

        return StringBuilder()
            .append("#")
            .append(hexVariables[red / 16])
            .append(hexVariables[red % 16])
            .append(hexVariables[green / 16])
            .append(hexVariables[green % 16])
            .append(hexVariables[blue / 16])
            .append(hexVariables[blue % 16])
            .toString()
    }
}