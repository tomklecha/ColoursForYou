package com.tkdev.coloursforyou.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tkdev.coloursforyou.R
import com.tkdev.coloursforyou.core.ColoursContract
import com.tkdev.coloursforyou.data.model.Colour
import com.tkdev.coloursforyou.presenter.ColoursPresenter

class MainActivity : AppCompatActivity(), ColoursContract.View {

    private lateinit var presenter: ColoursContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = ColoursServiceLocator(this).getPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.bind(this)
        presenter.onViewCreated()
    }

    override fun updateCurrentColours(colours: List<Colour>) {
        TODO("Not yet implemented")
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
