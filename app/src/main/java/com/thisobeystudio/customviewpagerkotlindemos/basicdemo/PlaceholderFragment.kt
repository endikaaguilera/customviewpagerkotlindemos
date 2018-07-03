package com.thisobeystudio.customviewpagerkotlindemos.basicdemo

/*
 * Created by Endika Aguilera on 13/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thisobeystudio.customviewpagerkotlin.models.CustomIndexHelper

import com.thisobeystudio.customviewpagerkotlindemos.R

import com.thisobeystudio.customviewpagerkotlindemos.demodata.DemoDataManager.getDarkerColor
import kotlinx.android.synthetic.main.fragment_demo_basic.view.*

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    companion object {
        /**
         * The fragment argument representing the section for this fragment.
         */
        private const val ARG_SECTION_HELPER = "section_helper"
        private const val ARG_DEMO_COLOR = "demo_color"

        /**
         * Returns a new instance of this fragment for the given section
         */
        fun newInstance(
                customIndexHelper: CustomIndexHelper,
                demoColor: Int): PlaceholderFragment {

            val fragment = PlaceholderFragment()
            val args = Bundle()
            args.putParcelable(ARG_SECTION_HELPER, customIndexHelper)
            args.putInt(ARG_DEMO_COLOR, demoColor)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_demo_basic, container, false)

        // check for null arguments
        if (arguments == null ||
                !arguments!!.containsKey(ARG_SECTION_HELPER) ||
                !arguments!!.containsKey(ARG_DEMO_COLOR)) return rootView

        // get page helper data (pager index and data index)
        val indexHelper: CustomIndexHelper = arguments!!.getParcelable(ARG_SECTION_HELPER)
        // get demo data (color)
        val color = arguments!!.getInt(ARG_DEMO_COLOR)

        val darkerColor = getDarkerColor(color)                         // get a darker color
        rootView.setBackgroundColor(darkerColor)                        // set rootView background

        rootView.basicDemoCardView?.setCardBackgroundColor(color)       // set cardView background

        val pagerPosition = indexHelper.pagerPosition.toString()        // get pager position
        val dataPosition = indexHelper.dataPosition.toString()          // get data position

        rootView.sectionPageIndexLabel?.text = pagerPosition            // set data index text

        rootView.sectionDataIndexLabel?.text = dataPosition             // set pager index text
        rootView.sectionDataIndexLabel?.setTextColor(darkerColor)       // set pager index textColor

        return rootView
    }
}