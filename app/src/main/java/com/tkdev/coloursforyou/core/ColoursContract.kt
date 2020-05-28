package com.tkdev.coloursforyou.core

import com.tkdev.coloursforyou.data.model.Colour

interface ColoursContract {

    interface View{
        fun updateCurrentColours(colours: List<Colour>?)
        fun showError(message: String)
    }

    interface Presenter{
        fun bind(view: View)
        fun unbind()
        fun onViewCreated()
        fun onButtonClicked()
        fun onViewSwiped()
    }

    interface Interactor{
        fun getSavedColours() : List<Colour>
        fun generateColours() : List<Colour>
    }

    interface Repository{
        fun getSavedColours() : List<Colour>
        fun getWords() : List<String>
        fun saveGeneratedColours(colours: List<Colour>)
    }


}