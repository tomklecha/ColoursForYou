package com.tkdev.coloursforyou.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tkdev.coloursforyou.core.ColoursContract
import com.tkdev.coloursforyou.data.model.Colour
import com.tkdev.coloursforyou.data.model.ColoursApi

class ColoursRepository(
    private val api: ColoursApi,
    private val sharedPreferences: SharedPreferences
) : ColoursContract.Repository {

    override fun getSavedColours(): List<Colour> {
        var colourList: List<Colour> = emptyList()
        with(sharedPreferences) {
            val gson = Gson()
            val json = sharedPreferences.getString("list", "")
            val type = object : TypeToken<List<Colour>>() {}.type
            if (json != null){
            colourList = gson.fromJson(json, type)
            }
        }

        return when (colourList.isEmpty()) {
            true -> emptyList()
            false -> colourList.toList()
        }
    }

    override fun getWords(): List<String> {
        return try {
            api.fetchData().toList()
        } catch (e: ExceptionInInitializerError) {
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