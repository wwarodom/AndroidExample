package com.warodom.hellokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvHello.setText("Hello Kotlin world!");
        tvHello.setOnClickListener {
            toast("Hello Kolin Toast");
        }

        // Alert box
        alert("Hi, I'm Roy", "Have you tried turning it off and on again?") {
            yesButton { toast("Oh…") }
            noButton {}
        }.show()

        // List box
        val countries = listOf("Russia", "USA", "Japan", "Australia")
        selector("Where are you from?", countries, { dialogInterface, i ->
            toast("So you're living in ${countries[i]}, right?")
        })

        val dialog = progressDialog(message = "Please wait a bit…", title = "Fetching data")
    }
}
