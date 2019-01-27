package com.samilaltin.loodos.asteriksproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var activeProcess = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnHitMe.setOnClickListener { attemptToStart() }

    }

    private fun attemptToStart() {
        if (activeProcess) {
            return
        }
        txtString.error = null
        txtAsteriks.error = null
        var cancel = false
        var focusView: View? = null

        val string = txtString.text.toString()
        val asteriks = txtAsteriks.text.toString()

        if (TextUtils.isEmpty(string)) run {
            txtString.error = getString(R.string.not_be_empty)
            focusView = txtString
            cancel = true
        }

        if (TextUtils.isEmpty(asteriks)) run {
            txtAsteriks.error = getString(R.string.not_be_empty)
            focusView = txtAsteriks
            cancel = true
        }

        if (cancel) {
            focusView?.requestFocus()
        } else {
            activeProcess = true

            start()
        }

    }

    private fun start() {

    }
}
