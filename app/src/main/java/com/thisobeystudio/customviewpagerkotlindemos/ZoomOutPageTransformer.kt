package com.thisobeystudio.customviewpagerkotlindemos

/*
 * Created by Endika Aguilera on 13/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.support.v4.view.ViewPager
import android.view.View

// Position of page relative to the current front-and-center position of the pager.
// 0 is front and center.
// 1 is one full page position to the right
// -1 is one page position to the left.
class ZoomOutPageTransformer : ViewPager.PageTransformer {

    companion object {
        private const val DEFAULT = 1f
        private const val MIN_SCALE = 0.90f
        private const val MIN_ALPHA = 0.6f
    }

    override fun transformPage(view: View, position: Float) {
        val width = view.width
        val height = view.height

        if (position == 0f || position > 1f || position < -1f) {
            view.scaleX = DEFAULT
            view.scaleY = DEFAULT
            view.alpha = DEFAULT
        } else {

            var scaleFactor = Math.max(MIN_SCALE, DEFAULT - Math.abs(position))
            val marginV = height.times((DEFAULT - scaleFactor)).div(2)
            val marginH = width.times((DEFAULT - scaleFactor)).div(2)

            when {
                position < 0 -> {
                    val posX = marginH.minus(marginV.div(2))
                    view.translationX = posX
                }
                position > 0 -> {
                    val posX = (-marginH).plus(marginV.div(2))
                    view.translationX = posX
                }
                else -> {
                    scaleFactor = 1f
                    view.translationX = 0f
                }
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.scaleX = scaleFactor
            view.scaleY = scaleFactor

            // Fade the page relative to its size.
            val alpha = MIN_ALPHA.plus(
                    (scaleFactor.minus(MIN_SCALE)).div((DEFAULT.minus(MIN_SCALE)))
                    .times((DEFAULT.minus(MIN_ALPHA))))

            view.alpha = alpha
        }
    }
}
