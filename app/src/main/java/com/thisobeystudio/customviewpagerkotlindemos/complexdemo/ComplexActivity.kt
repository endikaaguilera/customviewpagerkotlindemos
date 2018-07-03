package com.thisobeystudio.customviewpagerkotlindemos.complexdemo

/*
 * Created by Endika Aguilera on 13/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.thisobeystudio.customviewpagerkotlin.indicator.CustomIndicator.Companion.MODE_CLAMPED_HEIGHT
import com.thisobeystudio.customviewpagerkotlin.indicator.CustomIndicator.Companion.POSITION_FLOAT_BOTTOM

import com.thisobeystudio.customviewpagerkotlindemos.R
import com.thisobeystudio.customviewpagerkotlindemos.ZoomOutPageTransformer

import kotlinx.android.synthetic.main.activity_demos.*

class ComplexActivity : AppCompatActivity() {

    companion object {
        private val TAG = ComplexActivity::class.java.simpleName

        // demo data
        private fun complexDemoData(): Array<ComplexDataHelper> {
            val cdh = ComplexDataHelper()
            return arrayOf(
                    cdh.copy(color = Color.parseColor("#F44336")),
                    cdh.copy(color = Color.parseColor("#9C27B0")),
                    cdh.copy(color = Color.parseColor("#3F51B5")),
                    cdh.copy(color = Color.parseColor("#03A9F4")),
                    cdh.copy(color = Color.parseColor("#009688")),
                    cdh.copy(color = Color.parseColor("#8BC34A")),
                    cdh.copy(color = Color.parseColor("#FFEB3B")),
                    cdh.copy(color = Color.parseColor("#FF5722"))
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demos)

        title = TAG

        initCustomViewPager()
    }

    private fun initCustomViewPager() {
        // Create the adapter that will return a fragment for each of the
        // primary sections of the activity.
        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, complexDemoData())

        // Set up the CustomViewPager with the sections adapter.
        customViewPager?.adapter = sectionsPagerAdapter

        val maxRows = 2
        // Init pager indicators
        customViewPager?.initIndicators(POSITION_FLOAT_BOTTOM, MODE_CLAMPED_HEIGHT, maxRows)

        // Set pager transformer
        customViewPager?.setPageTransformer(true, ZoomOutPageTransformer())

        // Set initial selection
        customViewPager?.currentItem = 0
    }

    // region helper methods to share scroll pos between real and helper pages

    fun setHelperPageData(first: Boolean, last: Boolean, data: Any) {
        customViewPager?.setPageData(first, last, data)
    }

    fun getPageData(first: Boolean, last: Boolean): Any? {
        return customViewPager?.getPageData(first, last)
    }

    // endregion helper methods to share scroll pos between real and helper pages

}
