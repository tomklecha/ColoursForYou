package com.tkdev.coloursforyou.data

import android.content.SharedPreferences
import com.tkdev.coloursforyou.data.model.Colour
import com.tkdev.coloursforyou.data.model.ColoursApi
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*


class ColoursRepositoryTest {

    @MockK
    private lateinit var api: ColoursApi

    @MockK
    private lateinit var sharedPreferences: SharedPreferences

    @InjectMockKs
    private lateinit var repository: ColoursRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `GIVEN data, WHEN get saved data, THEN return colours list `() {
        //GIVEN

        val string =
            "[{\"word\":\"emotion\",\"colour\":\"sky\"},{\"word\":\"mischarge\",\"colour\":\"limit\"}]"
        val expected: List<Colour> =
            arrayListOf(
                Colour("emotion", "sky"),
                Colour("mischarge", "limit")
            )

        every { sharedPreferences.getString("list", null) } returns string


        //WHEN
        val result = repository.getSavedColours()

        //THEN
        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN empty data, WHEN get saved data, THEN return empty list `() {
        //GIVEN

        val savedData = null
        val expected: List<Colour> = emptyList()

        every { sharedPreferences.getString("list", null) } returns savedData


        //WHEN
        val result = repository.getSavedColours()

        //THEN
        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN list, WHEN get words, THEN return list `() {
        //GIVEN
        val list: List<String> = listOf("mom", "dad", "son", "daughter", "family")

        every { api.fetchData() } returns list

        //WHEN
        val result = repository.getWords()

        //THEN
        assertEquals(list, result)
    }

    @Test
    fun `GIVEN empty list, WHEN get words, THEN empty list `() {
        //GIVEN
        val list: List<String> = emptyList()

        every { api.fetchData() } returns list

        //WHEN
        val result = repository.getWords()

        //THEN
        assertEquals(list, result)
    }

}