package com.tkdev.coloursforyou.presenter

import com.tkdev.coloursforyou.core.ColoursContract
import com.tkdev.coloursforyou.data.model.Colour

class ColoursPresenter(
    private val interactor: ColoursContract.Interactor
) : ColoursContract.Presenter {

    private lateinit var view: ColoursContract.View
    private var result : List<Colour> = emptyList()


    override fun bind(view: ColoursContract.View) {
        this.view = view
    }

    override fun unbind() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated() {
       when(val savedData = interactor.getSavedColours()){
           emptyList<Colour>() -> view.showError("No previously saved data")
           else -> view.updateCurrentColours(savedData)
       }

    }

    override fun onButtonClicked() {
        Thread(Runnable { result = interactor.generateColours()}).start()

        when(result){
            emptyList<Colour>() -> view.showError("Problem with fetching data")
            else -> view.updateCurrentColours(result)
        }
    }

    override fun onViewSwiped() {
        when(val result = interactor.generateColours()){
            emptyList<Colour>() -> view.showError("Problem with fetching data")
            else -> view.updateCurrentColours(result)
        }
    }
}