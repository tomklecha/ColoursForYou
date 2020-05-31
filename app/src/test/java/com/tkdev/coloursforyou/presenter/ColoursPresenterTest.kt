package com.tkdev.coloursforyou.presenter

import com.tkdev.coloursforyou.core.ColoursContract
import com.tkdev.coloursforyou.core.CoroutineDispatcherFactory
import com.tkdev.coloursforyou.core.CoroutineDispatcherFactoryUnconfined
import com.tkdev.coloursforyou.data.model.Colour
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

class ColoursPresenterTest {

    @MockK
    private lateinit var view: ColoursContract.View

    @MockK
    private lateinit var interactor: ColoursContract.Interactor

    @MockK
    private lateinit var dispatcher: CoroutineDispatcherFactory

    @InjectMockKs
    private lateinit var presenter: ColoursPresenter

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)

        val unconfinedFactory = CoroutineDispatcherFactoryUnconfined()
        every { dispatcher.IO } returns unconfinedFactory.IO
        every { dispatcher.UI } returns unconfinedFactory.UI

        presenter.bind(view)
    }


    @Test
    fun `GIVEN no saved data, WHEN on view created, THEN show message no saved data`() {
        //GIVEN
        val message = "No previously saved data"

        val list: List<Colour> = emptyList()
        every { interactor.getSavedColours() } returns list

        //WHEN
        presenter.onViewCreated()

        //THEN
        verify {
            view.showError(message)
            view.updateListSizeView(list.size)
        }
    }

    @Test
    fun `GIVEN saved data, WHEN on view created, THEN update view with data`() {
        //GIVEN
        val list: List<Colour> = listOf(Colour("harry", "potter"), Colour("ron", "wesley"))

        every { interactor.getSavedColours() } returns list

        //WHEN
        presenter.onViewCreated()

        //THEN
        verify {
            view.updateCurrentColours(list)
            view.updateListSizeView(list.size)
        }
    }

    @Test
    fun `GIVEN success fetch data, WHEN on button pressed, THEN update view with new data`() {
        //GIVEN
        val list: List<Colour> = listOf(Colour("harry", "potter"), Colour("ron", "wesley"))

        coEvery { interactor.generateColours() } returns list

        //WHEN
        presenter.onButtonClicked()

        //THEN
        verify {
            view.updateCurrentColours(list)
            view.updateListSizeView(list.size)
        }
    }

    @Test
    fun `GIVEN failed fetch data, WHEN on button pressed, THEN update view with new data`() {
        //GIVEN
        val message = "Problem with fetching data"
        val list: List<Colour> = emptyList()

        coEvery { interactor.generateColours() } returns list

        //WHEN
        presenter.onButtonClicked()

        //THEN
        verify {
            view.showError(message)
            view.updateListSizeView(list.size)
        }
    }

    @Test
    fun `GIVEN success fetch data, WHEN on swipe view, THEN update view with new data`() {
        //GIVEN
        val list: List<Colour> = listOf(Colour("harry", "potter"), Colour("ron", "wesley"))

        coEvery { interactor.generateColours() } returns list

        //WHEN
        presenter.onViewSwiped()

        //THEN
        verify {
            view.updateCurrentColours(list)
            view.updateListSizeView(list.size)
        }
    }

    @Test
    fun `GIVEN failed fetch data, WHEN on swipe view, THEN update view with new data`() {
        //GIVEN
        val message = "Problem with fetching data"
        val list: List<Colour> = emptyList()

        coEvery { interactor.generateColours() } returns list

        //WHEN
        presenter.onViewSwiped()

        //THEN
        verify {
            view.showError(message)
            view.updateListSizeView(list.size)
        }
    }
}