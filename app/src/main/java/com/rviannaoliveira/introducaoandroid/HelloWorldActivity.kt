package com.rviannaoliveira.introducaoandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

/**
 * Criado por rodrigo on 19/09/17.
 */
class HelloWorldActivity : AppCompatActivity() {

    companion object {
        val KEY = "Nome"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bem_vindo)
        val textBemVindo = findViewById<TextView>(R.id.text_bem_vindo)
        textBemVindo.text = intent.getStringExtra(KEY)
    }
}