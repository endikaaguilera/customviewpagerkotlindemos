package com.thisobeystudio.customviewpagerkotlindemos.basicdemo

/*
 * Created by Endika Aguilera on 13/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.thisobeystudio.customviewpagerkotlindemos.R
import com.thisobeystudio.customviewpagerkotlindemos.demodata.DemoDataManager.demoColors
import kotlinx.android.synthetic.main.activity_demos.*

class BasicActivity : AppCompatActivity(){

    companion object {
        private val TAG = BasicActivity::class.java.simpleName
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

        // Set the initial pager selection
        customViewPager?.currentItem = 0
    }
}
