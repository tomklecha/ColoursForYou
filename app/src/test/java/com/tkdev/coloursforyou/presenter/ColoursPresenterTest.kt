package com.tkdev.coloursforyou.presenter

import com.tkdev.coloursforyou.core.ColoursContract
import com.tkdev.coloursforyou.data.model.Colour
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class ColoursPresenterTest{

    @MockK
    private lateinit var view: ColoursContract.View

    @MockK
    private lateinit var interactor: ColoursContract.Interactor

    @InjectMockKs
    private lateinit var presenter: ColoursPresenter

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        presenter.bind(view)
    }


    @Test
    fun `GIVEN no saved data, WHEN on view created, THEN show message no saved data`(){
        //GIVEN
        val message = "not this time my friend"

        //WHEN
        presenter.onViewCreated()

        //THEN
        view.showError(message)
    }

    @Test
    fun `GIVEN saved data, WHEN on view created, THEN update view with data`(){
        //GIVEN
        val list : List<Colour> = listOf(Colour("harry", -1), Colour("Potter", -1))

        every { interactor.getSavedColours() } returns list

        //WHEN
        presenter.onViewCreated()

        //THEN
        view.updateCurrentColours(list)
    }

    @Test
    fun `GIVEN internet connection AND success fetch data, WHEN on button pressed, THEN update view with new data`(){
        //GIVEN
        val list : List<Colour> = listOf(Colour("harry", -1), Colour("Potter", -1))

        every { interactor.generateColours() } returns list

        //WHEN
        presenter.onButtonClicked()

        //THEN
        view.updateCurrentColours(list)
    }

    @Test
    fun `GIVEN internet connection AND failed fetch data, WHEN on button pressed, THEN update view with new data`(){
        //GIVEN
        val message = "missing something?"
        val list : List<Colour> = emptyList()

        every { interactor.generateColours() } returns list

        //WHEN
        presenter.onButtonClicked()

        //THEN
        view.showError(message)
    }

    @Test
    fun `GIVEN no internet connection, WHEN on button pressed, THEN show error message`(){
        //GIVEN
        val message = "wrong way"

        //WHEN
        presenter.onButtonClicked()

        //THEN
        view.showError(message)
    }

    @Test
    fun `GIVEN internet connection AND success fetch data, WHEN on swipe view, THEN update view with new data`(){
        //GIVEN
        val list : List<Colour> = listOf(Colour("harry", -1), Colour("Potter", -1))

        every { interactor.generateColours() } returns list

        //WHEN
        presenter.onViewSwiped()

        //THEN
        view.updateCurrentColours(list)
    }

    @Test
    fun `GIVEN internet connection AND failed fetch data, WHEN on swipe view, THEN update view with new data`(){
        //GIVEN
        val message = "missing something?"
        val list : List<Colour> = emptyList()

        every { interactor.generateColours() } returns list

        //WHEN
        presenter.onViewSwiped()

        //THEN
        view.showError(message)
    }

    @Test
    fun `GIVEN no internet connection, WHEN on swipe view, THEN show error message`(){
        //GIVEN
        val message = "wrong way"

        //WHEN
        presenter.onViewSwiped()

        //THEN
        view.showError(message)
    }
}