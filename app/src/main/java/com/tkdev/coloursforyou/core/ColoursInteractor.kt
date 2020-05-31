package com.tkdev.coloursforyou.core

import com.tkdev.coloursforyou.data.ColoursHexGenerator
import com.tkdev.coloursforyou.data.model.Colour
import kotlin.random.Random


class ColoursInteractor(
    private val repository: ColoursContract.Repository,
    private val hexGenerator: ColoursHexGenerator
) : ColoursContract.Interactor {

    override fun getSavedColours(): List<Colour> {
        return when (val result = repository.getSavedColours()) {
            emptyList<Colour>() -> emptyList()
            else -> result
        }
    }

    override suspend fun generateColours(coloursQuantity : Int): List<Colour> {
        val result = repository.getWords(coloursQuantity)
        if (result == emptyList<String>()) {
            return emptyList()
        }

        val colourList = hexGenerator.generateHexColour(result)
        repository.saveGeneratedColours(colourList)
        return colourList
    }
}




