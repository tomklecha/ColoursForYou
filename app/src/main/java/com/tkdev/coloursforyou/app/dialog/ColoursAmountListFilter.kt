package com.tkdev.coloursforyou.app.dialog

import android.text.InputFilter
import android.text.Spanned

class ColoursAmountListFilter(startRange: Int, endRange: Int) : InputFilter {
    private var startRange: Int = 0
    private var endRange: Int = 0

    init {
        this.startRange = startRange
        this.endRange = endRange
    }

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            val input = (dest.subSequence(0, dstart).toString() + source + dest.subSequence(
                dend,
                dest.length
            )).toInt()
            if (isInRange(startRange, endRange, input))
                return null
        } catch (nfe: NumberFormatException) {
        }
        return ""
    }

    private fun isInRange(min: Int, max: Int, input: Int): Boolean {
        return if (max > min) input in min..max else input in max..min
    }
}