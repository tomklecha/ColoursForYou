package com.tkdev.coloursforyou.presenter

import com.tkdev.coloursforyou.core.ColoursContract

class ColoursPresenter(
    private val interactor: ColoursContract.Interactor
) : ColoursContract.Presenter {

    private lateinit var view: ColoursContract.View

    override fun bind(view: ColoursContract.View) {
        this.view = view
    }

    override fun unbind() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun onButtonClicked() {
        TODO("Not yet implemented")
    }

    override fun onViewSwiped() {
        TODO("Not yet implemented")
    }
}