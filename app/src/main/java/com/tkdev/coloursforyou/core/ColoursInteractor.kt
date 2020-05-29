package com.tkdev.coloursforyou.core

import com.tkdev.coloursforyou.data.model.Colour

class ColoursInteractor(
    private val repository: ColoursContract.Repository
) : ColoursContract.Interactor {

    override fun getSavedColours(): List<Colour> {
       return when(val result = repository.getSavedColours()){
            emptyList<Colour>() -> emptyList()
            else -> result
        }
    }

    override fun generateColours(): List<Colour> {
      return when(val result = repository.getWords()){
            emptyList<String>() -> emptyList()
            else -> createColourList(result)
        }
    }

    private fun createColourList(list: List<String>): List<Colour> {
        val colourList = emptyList<Colour>()

        list.forEach { i ->

        }

        return colourList
    }

    private fun randomizeColor() : String{

        return ""
    }

}