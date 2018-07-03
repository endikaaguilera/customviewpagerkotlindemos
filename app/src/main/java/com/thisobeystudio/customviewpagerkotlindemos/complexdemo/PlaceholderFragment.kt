package com.thisobeystudio.customviewpagerkotlindemos.complexdemo

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
import android.widget.RatingBar
import com.thisobeystudio.customviewpagerkotlin.models.CustomFragment
import com.thisobeystudio.customviewpagerkotlin.models.CustomIndexHelper


import com.thisobeystudio.customviewpagerkotlindemos.R

import com.thisobeystudio.customviewpagerkotlindemos.demodata.DemoDataManager.getDarkerColor
import kotlinx.android.synthetic.main.fragment_demo_complex.*
import kotlinx.android.synthetic.main.fragment_demo_complex.view.*

/**
 * A placeholder fragment containing a simple view.
 */
// TODO: notice that we are extending CustomFragment to share data between real and helper pages
class PlaceholderFragment :
        CustomFragment(),
        RatingBar.OnRatingBarChangeListener,
        NestedScrollView.OnScrollChangeListener {

    private var mActivity: ComplexActivity? = null

    companion object {
        /**
         * The fragment argument representing the section for this fragment.
         */
        private const val ARG_COMPLEX_DEMO_DATA = "complex_demo_data"

        /**
         * Returns a new instance of this fragment for the given section
         */
        fun newInstance(
                customIndexHelper: CustomIndexHelper,
                demoColor: ComplexDataHelper): PlaceholderFragment {

            val fragment = PlaceholderFragment()
            val args = Bundle()
            // todo this is important!! ARG_CUSTOM_INDEX_HELPER
            args.putParcelable(CustomFragment.ARG_CUSTOM_INDEX_HELPER, customIndexHelper)
            args.putParcelable(ARG_COMPLEX_DEMO_DATA, demoColor)
            fragment.arguments = args
            return fragment
        }
    }

    private var mComplexDataHelper: ComplexDataHelper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_demo_complex, container, false)

        // make sure we are targeting the right host activity
        mActivity = (activity!! as? ComplexActivity) ?: return rootView

        // check for valid arguments
        if (arguments == null ||
                arguments?.containsKey(ARG_CUSTOM_INDEX_HELPER) == false ||
                arguments?.containsKey(ARG_COMPLEX_DEMO_DATA) == false)
            return rootView

        // get ComplexDataHelper from arguments
        mComplexDataHelper = arguments!!.getParcelable(ARG_COMPLEX_DEMO_DATA)

        val color = mComplexDataHelper?.color!!

        // set root view background color
        val darkerColor = getDarkerColor(color)
        rootView.setBackgroundColor(darkerColor)

        // set complexDemoCardView background color
        rootView.complexDemoCardView.setCardBackgroundColor(color)

        // set sectionPageIndexLabel text
        rootView.sectionPageIndexLabel.text = pageIndex.toString()

        // set sectionDataIndexLabel text and text color
        rootView.sectionDataIndexLabel.text = dataIndex.toString()
        rootView.sectionDataIndexLabel.setTextColor(darkerColor)

        // handle data if we are on real first and/or last page
        if (isRealFirst || isRealLast) {
            // handle rating bar
            rootView.ratingBar.onRatingBarChangeListener = this@PlaceholderFragment

            // handle scroll view
            rootView.complexDemoScrollView.setOnScrollChangeListener(this@PlaceholderFragment)
        }

        // handle data if we are on helper first and/or last page
        if (isHelperFirst || isHelperLast) updateHelpers(rootView!!)

        return rootView
    }

    // handle rating change to share data between real and helper pages
    override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
        // update complexDataHelper rating value
        mComplexDataHelper?.rating = rating
        // update page data
        mActivity?.setHelperPageData(isRealFirst, isRealLast, mComplexDataHelper!!)
    }

    // handle scroll change to share data between real and helper pages
    override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        // update complexDataHelper posY value
        mComplexDataHelper?.posY = scrollY
        // update page data
        mActivity?.setHelperPageData(isRealFirst, isRealLast, mComplexDataHelper!!)
    }

    // this will be called if the helper fragment is available to update its data
    override fun setHelperPageData(data: Any) {
        mComplexDataHelper = data as? ComplexDataHelper ?: return
        ratingBar?.rating = mComplexDataHelper!!.rating
        complexDemoScrollView?.scrollY = mComplexDataHelper!!.posY
    }

    // use to update ui when needed
    private fun updateHelpers(rootView: View?) {
        mComplexDataHelper = mActivity?.getPageData(isHelperFirst, isHelperLast)
                as? ComplexDataHelper ?: return
        rootView?.post {
            ratingBar?.rating = mComplexDataHelper!!.rating
            complexDemoScrollView?.scrollY = mComplexDataHelper!!.posY
        }
    }
}