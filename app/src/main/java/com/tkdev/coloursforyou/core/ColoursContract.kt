package com.tkdev.coloursforyou.core

import com.tkdev.coloursforyou.data.model.Colour

interface ColoursContract {

    interface View {
        fun updateCurrentColours(colours: List<Colour>)
        fun updateListSizeView(size: Int)
        fun showError(message: String)
    }

    interface Presenter {
        fun bind(view: View)
        fun unbind()
        fun onViewCreated()
        fun onButtonClicked(coloursQuantity : Int)
        fun onViewSwiped(coloursQuantity : Int)
        fun onDialogPositiveUpdate(coloursQuantity: Int)
    }

    interface Interactor {
        fun getSavedColours(): List<Colour>
        suspend fun generateColours(coloursQuantity : Int): List<Colour>
    }

    interface Repository {
        fun getSavedColours(): List<Colour>
        suspend fun getWords(coloursQuantity : Int): List<String>
        fun saveGeneratedColours(colours: List<Colour>)
    }


}