package com.tkdev.coloursforyou.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tkdev.coloursforyou.R
import com.tkdev.coloursforyou.core.ColoursContract
import com.tkdev.coloursforyou.data.model.Colour
import kotlinx.android.synthetic.main.activity_main.*

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

        coloursRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

    }

    override fun updateCurrentColours(colours: List<Colour>) {
        coloursRecyclerView.adapter = ColoursAdapter(colours)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
