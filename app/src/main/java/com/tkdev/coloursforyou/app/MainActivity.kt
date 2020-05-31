package com.tkdev.coloursforyou.app

import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MotionEventCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tkdev.coloursforyou.R
import com.tkdev.coloursforyou.app.dialog.ColoursListSizeDialog
import com.tkdev.coloursforyou.core.ColoursContract
import com.tkdev.coloursforyou.data.model.Colour
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ColoursContract.View,
    ColoursListSizeDialog.ColoursListSizeDialogListener {

    private lateinit var presenter: ColoursContract.Presenter
    private lateinit var dialog: DialogFragment

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

        updateListSize.setOnClickListener {
            dialog = ColoursListSizeDialog()
            dialog.show(supportFragmentManager, "ColoursListSizeDialog")
        }

        fetchData.setOnClickListener { presenter.onButtonClicked(
            textView.text.toString().toInt()
        ) }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (MotionEventCompat.getActionMasked(event)) {
            MotionEvent.ACTION_DOWN -> {
                presenter.onViewSwiped(textView.text.toString().toInt())
                true
            }
            else -> super.onTouchEvent(event)
        }
    }

    override fun updateCurrentColours(colours: List<Colour>) {
        coloursRecyclerView.adapter = ColoursAdapter(colours)
    }

    override fun updateListSizeView(size: Int) {
        textView.text = size.toString()
    }


    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onPositiveClick(coloursQuantity: Int) {
        presenter.onDialogPositiveUpdate(coloursQuantity)
    }

    override fun onNegativeClick(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
