package com.tkdev.coloursforyou.core

import com.tkdev.coloursforyou.data.model.Colour

class ColoursInteractor(
    private val repository: ColoursContract.Repository
) : ColoursContract.Interactor {

    override fun getSavedColours(): List<Colour> {
        TODO("Not yet implemented")
    }

    override fun generateColours(): List<Colour> {
        TODO("Not yet implemented")
    }

}