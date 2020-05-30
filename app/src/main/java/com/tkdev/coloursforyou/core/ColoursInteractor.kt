package com.tkdev.coloursforyou.core

import com.tkdev.coloursforyou.data.model.Colour
import kotlin.random.Random


class ColoursInteractor(
    private val repository: ColoursContract.Repository
) : ColoursContract.Interactor {

    override fun getSavedColours(): List<Colour> {
        return when (val result = repository.getSavedColours()) {
            emptyList<Colour>() -> emptyList()
            else -> result
        }
    }

    override fun generateColours(): List<Colour> {
        val result = repository.getWords()
        if (result == emptyList<String>()) {
            return emptyList()
        }
        val colourList = createColourList(result)
        Thread(Runnable { repository.saveGeneratedColours(colourList) }).start()
        return colourList
    }

}

private fun createColourList(list: List<String>): List<Colour> {
    val colourList = ArrayList<Colour>()

    list.forEach {
        colourList.add(Colour(it, randomizeColor()))
    }

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

