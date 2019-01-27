package com.samilaltin.loodos.asteriksproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var activeProcess = false
    private var asteriks = 0
    private val stringList = ArrayList<String>()
    private var text = ""

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
            activeProcess = false
            start()
        }

    }

    private fun start() {
        asteriks = Integer.parseInt(txtAsteriks.text.toString())
        val stringList = howManyRepeating()
        setAsteriks(stringList)
    }

    private fun howManyRepeating(): ArrayList<String> {
        val charArray = txtString!!.text.toString().toCharArray()
        for (char in charArray) {
            if (stringList.size == 0) {
                val string = String(char, 1)
                stringList.add(string)
            } else {
                if (stringList[stringList.size - 1].string == char) {
                    stringList[stringList.size - 1].addAsteriks()
                } else {
                    val string = String(char, 1)
                    stringList.add(string)
                }
            }
        }
        return stringList
    }

    private fun setAsteriks(stringList: ArrayList<String>) {
        for (strings in stringList) {
            if (strings.asteriks >= asteriks) {
                for (i in 0 until strings.asteriks) {
                    text += "*"
                }
            } else {
                for (i in 0 until strings.asteriks) {
                    text += strings.string
                }
            }
        }

        txtResult!!.text = text
    }

    class String(internal var string: Char, internal var asteriks: Int) {
        fun addAsteriks() {
            this.asteriks += 1
        }
    }
}
