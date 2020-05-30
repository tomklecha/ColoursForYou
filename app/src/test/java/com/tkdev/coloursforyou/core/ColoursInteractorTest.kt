package com.tkdev.coloursforyou.core

import com.tkdev.coloursforyou.data.ColoursHexGenerator
import com.tkdev.coloursforyou.data.model.Colour
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ColoursInteractorTest {

    @MockK
    private lateinit var repository: ColoursContract.Repository

    @MockK
    private lateinit var hexGenerator: ColoursHexGenerator

    @InjectMockKs
    private lateinit var interactor: ColoursInteractor

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `GIVEN list, WHEN get saved data called, THEN return list`() {
        //GIVEN
        val expected: List<Colour> = listOf(Colour("harry", "potter"), Colour("ron", "wesley"))

        every { repository.getSavedColours() } returns expected

        //WHEN
        val result = interactor.getSavedColours()

        //THEN
        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN empty list, WHEN get saved data called, THEN return empty list`() {
        //GIVEN
        val expected: List<Colour> = emptyList()

        every { repository.getSavedColours() } returns expected

        //WHEN
        val result = interactor.getSavedColours()

        //THEN
        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN words list , WHEN generate colours, THEN return list AND save list`() {
        //GIVEN
        val list: List<String> = listOf("mom", "dad", "son", "daughter", "family")
        val expected: List<Colour> =
            listOf(
                Colour("mom", "sky"),
                Colour("dad", "is"),
                Colour("son", "the"),
                Colour("daughter", "only"),
                Colour("family", "limit")
            )

        coEvery { repository.getWords() } returns list
        every { hexGenerator.generateHexColour(list) } returns expected

        //WHEN
        val result = runBlocking { interactor.generateColours() }

        //THEN
        assertEquals(expected, result)

        verify {
            repository.saveGeneratedColours(expected)
        }
    }

    @Test
    fun `GIVEN empty words list , WHEN generate colours, THEN return empty list`() {
        //GIVEN
        val list: List<String> = emptyList()
        val expected: List<Colour> = emptyList()

        coEvery { repository.getWords() } returns list

        //WHEN
        val result = runBlocking { interactor.generateColours() }

        //THEN
        assertEquals(expected, result)

        verify(exactly = 0) {
            repository.saveGeneratedColours(expected)
        }
    }


}