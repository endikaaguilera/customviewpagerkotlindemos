package com.thisobeystudio.customviewpagerkotlindemos.scrollabledemo

/*
 * Created by Endika Aguilera on 13/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.thisobeystudio.customviewpagerkotlin.indicator.CustomIndicator.Companion.MODE_CLAMPED_HEIGHT
import com.thisobeystudio.customviewpagerkotlin.indicator.CustomIndicator.Companion.POSITION_FLOAT_BOTTOM

import com.thisobeystudio.customviewpagerkotlindemos.R
import com.thisobeystudio.customviewpagerkotlindemos.demodata.DemoDataManager.demoColors
import kotlinx.android.synthetic.main.activity_demos.*

class ScrollableActivity : AppCompatActivity() {

    companion object {
        private val TAG = ScrollableActivity::class.java.simpleName
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
        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, demoColors())

        // Set up the CustomViewPager with the sections adapter.
        customViewPager?.adapter = sectionsPagerAdapter

        // Indicators must be initialized before set the initial CustomViewPager current item.
        customViewPager?.initIndicators(POSITION_FLOAT_BOTTOM, MODE_CLAMPED_HEIGHT)

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
