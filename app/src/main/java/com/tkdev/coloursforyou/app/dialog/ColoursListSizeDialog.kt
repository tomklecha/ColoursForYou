package com.tkdev.coloursforyou.app.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.tkdev.coloursforyou.R
import kotlinx.android.synthetic.main.dialog_list_size.view.*

class ColoursListSizeDialog : DialogFragment() {

    private lateinit var listener: ColoursListSizeDialogListener

    interface ColoursListSizeDialogListener {
        fun onPositiveClick(colourQuantity: Int)
        fun onNegativeClick(message: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as ColoursListSizeDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                (context.toString() +
                        " must implement ColoursListSizeDialogListener")
            )
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder: AlertDialog.Builder? = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogLayout = inflater.inflate(R.layout.dialog_list_size, null)
            val editText = dialogLayout.editText
            editText.filters = arrayOf(ColoursAmountListFilter(1, 99))

            builder?.setView(dialogLayout)

            builder?.setPositiveButton("apply")
            { _, _ ->
                listener.onPositiveClick(editText.text.toString().toInt())
            }
            builder?.setNegativeButton("cancel")
            { _, _ ->
                listener.onNegativeClick("Update of colour list cancelled")
            }

            builder?.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}