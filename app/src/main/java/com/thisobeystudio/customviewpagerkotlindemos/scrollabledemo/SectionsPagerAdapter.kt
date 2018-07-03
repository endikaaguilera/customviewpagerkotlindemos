package com.thisobeystudio.customviewpagerkotlindemos.scrollabledemo

/*
 * Created by Endika Aguilera on 13/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

import com.thisobeystudio.customviewpagerkotlin.models.CustomIndexHelper
import com.thisobeystudio.customviewpagerkotlin.viewpager.CustomPagerAdapter

/**
 * A [CustomPagerAdapter] that returns a fragment corresponding to one of the pages.
 * Note: Do NOT use getCount(Int), use getRealCount(Int)
 */
class SectionsPagerAdapter(
        fm: FragmentManager, private val mDemoColors: IntArray) :
        CustomPagerAdapter(fm) {

    public override fun getItem(customIndexHelper: CustomIndexHelper): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment.
        return PlaceholderFragment
                .newInstance(customIndexHelper, mDemoColors[customIndexHelper.dataPosition])
    }

    // determines the real number of 'unique' pages, (first and last are duplicated)
    override fun getRealCount(): Int {
        return mDemoColors.size
    }

}