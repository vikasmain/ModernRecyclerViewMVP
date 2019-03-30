package com.cogoport.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.cogoport.R

class KotlinActivity:AppCompatActivity(){
    private val higherOrderFunctions:TextView
    val lambdas:TextView
    init {
        higherOrderFunctions=findViewById(R.id.kotlinHigherOrderFunctions)
        lambdas=findViewById(R.id.kotlinLambdas)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
    }
}