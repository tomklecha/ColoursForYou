package com.tkdev.coloursforyou.presenter

import com.tkdev.coloursforyou.core.ColoursContract
import com.tkdev.coloursforyou.core.CoroutineDispatcherFactory
import com.tkdev.coloursforyou.data.model.Colour
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ColoursPresenter(
    private val interactor: ColoursContract.Interactor,
    private val dispatcher: CoroutineDispatcherFactory
) : ColoursContract.Presenter, CoroutineScope {

    private lateinit var view: ColoursContract.View

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = dispatcher.UI + job

    override fun bind(view: ColoursContract.View) {
        this.view = view
    }

    override fun unbind() {
        job.cancel()
    }

    override fun onViewCreated() {
       when(val savedData = interactor.getSavedColours()){
           emptyList<Colour>() -> showError("No previously saved data")
           else -> updateColors(savedData)
       }
    }

    override fun onButtonClicked() {
        generateColourList()
    }

    override fun onViewSwiped() {
        generateColourList()
    }

    private fun CoroutineScope.generateColourList() = launch(dispatcher.IO){
        when(val result = interactor.generateColours()){
            emptyList<Colour>() -> showError("Problem with fetching data")
            else -> updateColors(result)
        }
    }

    private fun CoroutineScope.updateColors(colours: List<Colour>) = launch(dispatcher.UI){
        view.updateCurrentColours(colours)
    }

    private fun CoroutineScope.showError(message: String) = launch(dispatcher.UI){
        view.showError(message)
    }
}