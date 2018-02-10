package com.cogoport.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.cogoport.R
import com.cogoport.adapter.CustomKotAdapter
import com.cogoport.model.Model
class KotlinActivity : AppCompatActivity() {
    var dataModel:ArrayList<Model> = ArrayList()
    var adapter:CustomKotAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


        val users = ArrayList<Model>()

        var name_list = arrayOf("OverView","Data Types","Control Flow","Classes","Inheritance","Interface")
        var age_list = arrayOf("Kotlin Introduction","Different data types in kotlin","if else for,while loop",
                "classes and objects","inherit classes","interfaces in kotlin")

        for(i in 0..name_list.size-1){
            dataModel.add(Model(name_list[i],age_list[i]))
        }
        adapter = CustomKotAdapter(applicationContext,dataModel)
        var layout_manager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layout_manager
        recyclerView.adapter = adapter

    }
}