package com.tkdev.coloursforyou.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tkdev.coloursforyou.core.ColoursContract
import com.tkdev.coloursforyou.data.model.Colour
import com.tkdev.coloursforyou.data.model.ColoursApi
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ColoursRepository(
    private val api: ColoursApi,
    private val sharedPreferences: SharedPreferences
) : ColoursContract.Repository {

    override fun getSavedColours(): List<Colour> {
        var colourList: List<Colour> = emptyList()
        with(sharedPreferences) {
            val json = sharedPreferences.getString("list", null)
            if (json != null) {
                val gson = Gson()
                val type = object : TypeToken<List<Colour>>() {}.type
                colourList = gson.fromJson(json, type)
            }
        }

        return when (colourList.isEmpty()) {
            true -> emptyList()
            false -> colourList
        }
    }

    override suspend fun getWords(coloursQuantity : Int): List<String> {
        return try {
            api.fetchData(coloursQuantity).toList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun saveGeneratedColours(colours: List<Colour>) {
        with(sharedPreferences.edit()){
            val gson = Gson()
            val json = gson.toJson(colours)
            putString("list", json)
        }.apply()
    }
}