package com.rao.weatherapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rao.weatherapp.R
import kotlinx.android.synthetic.main.forecast_items.view.*

class ForecastAdpater(var list: MutableList<Pair<String, String>>, var context: Context) :
        RecyclerView.Adapter<ForecastAdpater.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.forecast_items, parent,
                false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.day_title.text = list.get(position).first
        holder.day_temp.text = list.get(position).second
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var day_title = view.day
        var day_temp = view.temp
    }
}