package com.tkdev.coloursforyou.app

import android.content.Context
import com.tkdev.coloursforyou.core.ColoursContract
import com.tkdev.coloursforyou.core.ColoursInteractor
import com.tkdev.coloursforyou.core.CoroutineDispatcherFactory
import com.tkdev.coloursforyou.core.CoroutineDispatcherFactoryDefault
import com.tkdev.coloursforyou.data.ColoursHexGenerator
import com.tkdev.coloursforyou.data.ColoursHexGeneratorDefault
import com.tkdev.coloursforyou.data.ColoursRepository
import com.tkdev.coloursforyou.data.model.ColoursApi
import com.tkdev.coloursforyou.data.model.ColoursApiDefault
import com.tkdev.coloursforyou.presenter.ColoursPresenter

class ColoursServiceLocator (private val context: Context){

    fun getPresenter(): ColoursContract.Presenter = ColoursPresenter(getInteractor(), getCoroutineDispatcher())

    private fun getInteractor(): ColoursContract.Interactor = ColoursInteractor(getRepository(), getHexGenerator())

    private fun getHexGenerator(): ColoursHexGenerator = ColoursHexGeneratorDefault()

    private fun getRepository(): ColoursContract.Repository = ColoursRepository(getApi(), context.getSharedPreferences("list",Context.MODE_PRIVATE))

    private fun getCoroutineDispatcher(): CoroutineDispatcherFactory = CoroutineDispatcherFactoryDefault()

    private fun getApi(): ColoursApi = ColoursApiDefault()

}