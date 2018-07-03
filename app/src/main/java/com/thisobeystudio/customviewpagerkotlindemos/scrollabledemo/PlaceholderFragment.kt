package com.thisobeystudio.customviewpagerkotlindemos.scrollabledemo

/*
 * Created by Endika Aguilera on 13/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thisobeystudio.customviewpagerkotlin.models.CustomFragment
import com.thisobeystudio.customviewpagerkotlin.models.CustomIndexHelper

import com.thisobeystudio.customviewpagerkotlindemos.R

import com.thisobeystudio.customviewpagerkotlindemos.demodata.DemoDataManager.getDarkerColor
import kotlinx.android.synthetic.main.fragment_demo_scrollable.*
import kotlinx.android.synthetic.main.fragment_demo_scrollable.view.*

/**
 * A placeholder fragment containing a simple view.
 */

// TODO: notice that we are extending CustomFragment to share data between real and helper pages
class PlaceholderFragment : CustomFragment(), NestedScrollView.OnScrollChangeListener {

    private var mActivity: ScrollableActivity? = null

    companion object {
        /**
         * The fragment argument representing the section for this fragment.
         */
        private const val ARG_DEMO_COLOR = "demo_color"

        /**
         * Returns a new instance of this fragment for the given section
         */
        fun newInstance(customIndexHelper: CustomIndexHelper,
                        demoColor: Int): PlaceholderFragment {
            val fragment = PlaceholderFragment()
            val args = Bundle()
            // fixme this is important!! ARG_CUSTOM_INDEX_HELPER
            args.putParcelable(ARG_CUSTOM_INDEX_HELPER, customIndexHelper)
            args.putInt(ARG_DEMO_COLOR, demoColor)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_demo_scrollable, container, false)

        // make sure we are targeting the right host activity
        mActivity = (activity!! as? ScrollableActivity) ?: return rootView

        // check for valid arguments
        if (arguments == null ||
                arguments?.containsKey(ARG_CUSTOM_INDEX_HELPER) == false ||
                arguments?.containsKey(ARG_DEMO_COLOR) == false)
            return rootView

        // set scrollableDemoCardView background color
        val color = arguments?.getInt(ARG_DEMO_COLOR)!!
        rootView.scrollableDemoCardView.setCardBackgroundColor(color)

        // set root view background color
        val darkerColor = getDarkerColor(color)
        rootView.setBackgroundColor(darkerColor)

        // set sectionPageIndexLabel text
        rootView.sectionPageIndexLabel.text = pageIndex.toString()

        // set sectionDataIndexLabel text and color
        rootView.sectionDataIndexLabel.text = dataIndex.toString()
        rootView.sectionDataIndexLabel.setTextColor(darkerColor)

        // handle data if we are on first or last real pages
        if (isRealFirst || isRealLast)
            rootView.scrollableDemoScrollView.setOnScrollChangeListener(this@PlaceholderFragment)

        // handle data if we are on first or last helper pages
        if (isHelperFirst || isHelperLast) updateHelpers(rootView!!)

        return rootView!!
    }

    // handle scroll change to share data between real and helper pages
    override fun onScrollChange(v: NestedScrollView?,
                                scrollX: Int, scrollY: Int,
                                oldScrollX: Int, oldScrollY: Int) {
        mActivity?.setHelperPageData(isRealFirst, isRealLast, scrollY) // update page data
    }

    // this will be called if the helper fragment is available to update its data
    override fun setHelperPageData(data: Any) {
        val posY = data as? Int ?: 0
        scrollableDemoScrollView.scrollY = posY
    }

    // use to update ui when needed
    private fun updateHelpers(rootView: View?) {
        val data = mActivity?.getPageData(isHelperFirst, isHelperLast)

        val posY = data as? Int ?: return

        rootView?.post {
            scrollableDemoScrollView.scrollY = posY
        }
    }
}