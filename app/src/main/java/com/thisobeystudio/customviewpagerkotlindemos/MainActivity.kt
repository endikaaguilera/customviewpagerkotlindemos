package com.thisobeystudio.customviewpagerkotlindemos

/*
 * Created by Endika Aguilera on 13/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.content.Intent
import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import com.thisobeystudio.customviewpagerkotlindemos.basicdemo.BasicActivity
import com.thisobeystudio.customviewpagerkotlindemos.basicdemo.IndicatorsActivity
import com.thisobeystudio.customviewpagerkotlindemos.complexdemo.ComplexActivity
import com.thisobeystudio.customviewpagerkotlindemos.scrollabledemo.ScrollableActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = getString(R.string.app_name)

        basicBtn.setOnClickListener(this)
        indicatorsBtn.setOnClickListener(this)
        complexBtn.setOnClickListener(this)
        scrollableBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        val intent: Intent

        val id = v.id

        when (id) {
            R.id.basicBtn -> {
                intent = Intent(this@MainActivity, BasicActivity::class.java)
                startActivity(intent)
            }
            R.id.indicatorsBtn -> {
                intent = Intent(this@MainActivity, IndicatorsActivity::class.java)
                startActivity(intent)
            }
            R.id.scrollableBtn -> {
                intent = Intent(this@MainActivity, ScrollableActivity::class.java)
                startActivity(intent)
            }
            R.id.complexBtn -> {
                intent = Intent(this@MainActivity, ComplexActivity::class.java)
                startActivity(intent)
            }
            else -> {
            }
        }
    }
}
