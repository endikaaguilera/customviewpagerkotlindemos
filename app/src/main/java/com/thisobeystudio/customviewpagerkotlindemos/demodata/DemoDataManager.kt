package com.thisobeystudio.customviewpagerkotlindemos.demodata

/*
 * Created by Endika Aguilera on 13/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.graphics.Color

object DemoDataManager {

    @JvmStatic
    fun demoColors() : IntArray {
        return intArrayOf(
                Color.parseColor("#F44336"),
                Color.parseColor("#9C27B0"),
                Color.parseColor("#3F51B5"),
                Color.parseColor("#03A9F4"),
                Color.parseColor("#009688"),
                Color.parseColor("#8BC34A"),
                Color.parseColor("#FFEB3B"),
                Color.parseColor("#FF5722")
        )
    }

    @JvmStatic
    fun getDarkerColor(baseColor: Int): Int {
        val hsv = FloatArray(3)
        var color = baseColor
        Color.colorToHSV(color, hsv)
        hsv[2] *= 0.6f
        color = Color.HSVToColor(hsv)
        return color
    }
}
