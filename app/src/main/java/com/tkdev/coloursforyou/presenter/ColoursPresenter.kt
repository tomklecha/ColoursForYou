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
        when (val savedData = interactor.getSavedColours()) {
            emptyList<Colour>() -> {
                showError("No previously saved data")
                updateColoursListSize(savedData.size)
            }
            else -> {
                updateColors(savedData)
                updateColoursListSize(savedData.size)
                showSuccess("Successfully loaded saved data")
            }
        }
    }

    override fun onButtonClicked(coloursQuantity: Int) {
        generateColourList(coloursQuantity)
    }

    override fun onViewSwiped(coloursQuantity: Int) {
        generateColourList(coloursQuantity)
    }

    override fun onDialogPositiveUpdate(coloursQuantity: Int) {
        generateColourList(coloursQuantity)
    }

    private fun CoroutineScope.generateColourList(coloursQuantity: Int) = launch(dispatcher.IO) {
        if (coloursQuantity == 0) {
            showError("Please enter size list of colours first")
        } else {
            when (val result = interactor.generateColours(coloursQuantity)) {
                emptyList<Colour>() -> {
                    showError("Problem with fetching data")
                    updateSwipeRefresh(false)
                }
                else -> {
                    updateColors(result)
                    updateColoursListSize(result.size)
                    updateSwipeRefresh(false)
                    showSuccess("Successfully generated new colour list")
                }
            }
        }
    }

    private fun CoroutineScope.updateColors(colours: List<Colour>) = launch(dispatcher.UI) {
        view.updateCurrentColours(colours)
    }

    private fun CoroutineScope.showError(message: String) = launch(dispatcher.UI) {
        view.showError(message)
    }

    private fun CoroutineScope.showSuccess(message: String) = launch(dispatcher.UI) {
        view.showSuccess(message)
    }

    private fun CoroutineScope.updateColoursListSize(listSize: Int) = launch(dispatcher.UI) {
        view.updateListSizeView(listSize)
    }

    private fun CoroutineScope.updateSwipeRefresh(value: Boolean) = launch(dispatcher.UI) {
        view.setSwipeRefresh(value)
    }
}