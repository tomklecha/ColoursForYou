package com.tkdev.coloursforyou.data.model

interface ColoursApi {
    fun fetchData(): List<String>
}

class ColoursApiDefault() : ColoursApi {
    override fun fetchData(): List<String> {
        TODO("Not yet implemented")
    }

}