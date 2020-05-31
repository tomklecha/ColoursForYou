package com.tkdev.coloursforyou.app.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.tkdev.coloursforyou.R


class DevDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(R.layout.dialog_about_dev, null))
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
