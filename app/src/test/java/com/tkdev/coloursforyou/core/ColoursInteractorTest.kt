package com.tkdev.coloursforyou.core

import com.tkdev.coloursforyou.data.model.Colour
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ColoursInteractorTest{

    @MockK
    private lateinit var repository: ColoursContract.Repository

    @InjectMockKs
    private lateinit var interactor: ColoursInteractor

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `GIVEN list, WHEN get saved data called, THEN return list`(){
        //GIVEN
        val expected : List<Colour> = listOf(Colour("harry", -1), Colour("Potter", -1))

        every { repository.getSavedColours() } returns expected

        //WHEN
        val result = interactor.getSavedColours()

        //THEN
        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN empty list, WHEN get saved data called, THEN return empty list`(){
        //GIVEN
        val expected : List<Colour> = emptyList()

        every { repository.getSavedColours() } returns expected

        //WHEN
        val result = interactor.getSavedColours()

        //THEN
        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN words list , WHEN generate colours, THEN return list AND save list`(){
        //GIVEN
        val list : List<String> = listOf("mom", "dad","son", "daughter", "family")
        val expected : List<Colour> =
            listOf(Colour("mom", -1),
                    Colour("dad", -2),
                    Colour("son", -3),
                    Colour("daughter", -4),
                    Colour("family", -5))

        every { repository.getWords() } returns list

        //WHEN
        val result = interactor.generateColours()

        //THEN
        assertEquals(expected, result)

        verify {
            repository.saveGeneratedColours(expected)
        }
    }

    @Test
    fun `GIVEN empty words list , WHEN generate colours, THEN return empty list`(){
        //GIVEN
        val list : List<String>  = emptyList()
        val expected : List<Colour> = emptyList()

        every { repository.getWords() } returns list

        //WHEN
        val result = interactor.generateColours()

        //THEN
        assertEquals(expected, result)

        verify(exactly = 0) {
            repository.saveGeneratedColours(expected)
        }
    }


}