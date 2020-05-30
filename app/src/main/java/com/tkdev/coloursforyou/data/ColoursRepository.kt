package com.tkdev.coloursforyou.data

import android.content.SharedPreferences
import com.tkdev.coloursforyou.core.ColoursContract
import com.tkdev.coloursforyou.data.model.Colour
import com.tkdev.coloursforyou.data.model.ColoursApi

class ColoursRepository(
    private val api: ColoursApi,
    private val sharedPreferences: SharedPreferences
) : ColoursContract.Repository {

    override fun getSavedColours(): List<Colour> {
        TODO("Not yet implemented")
    }

    override fun getWords(): List<String> {
        TODO("Not yet implemented")
    }

    override fun saveGeneratedColours(colours: List<Colour>) {
        TODO("Not yet implemented")
    }
}