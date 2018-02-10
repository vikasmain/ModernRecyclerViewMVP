package com.cogoport.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cogoport.R
import com.cogoport.model.Model
import com.cogoport.view.SecondActivity


class CustomKotAdapter(var c: Context,val userList: ArrayList<Model>) : RecyclerView.Adapter<CustomKotAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomKotAdapter.ViewHolder {

                val v = LayoutInflater.from(parent.context).inflate(R.layout.card_kot_items, parent, false)
        return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: CustomKotAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position])
                holder.setOnCostomItemClickListener(object :ItemClickListener{

                        override fun onCostomItemClickListener(view: View, pos: Int) {
                                var url: Array<String> = arrayOf("https://www.tutorialspoint.com/kotlin/kotlin_overview.htm"
                                        ,"https://www.tutorialspoint.com/kotlin/kotlin_basic_types.htm"
                                        ,"https://www.tutorialspoint.com/kotlin/kotlin_control_flow.htm"
                                        ,"https://www.tutorialspoint.com/kotlin/kotlin_class_and_object.htm"
                                ,"https://www.tutorialspoint.com/kotlin/kotlin_inheritance.htm"
                                ,"https://www.tutorialspoint.com/kotlin/kotlin_interface.htm")
                           val intent=Intent(c,SecondActivity::class.java).putExtra("com.cogoport.adapter",url[pos])
                                   .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                c.startActivity(intent)
                        }
                })
        }

        override fun getItemCount(): Int {
        return userList.size
        }

//the class is hodling the list view
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        var costomItemClickListener:ItemClickListener?=null

        fun bindItems(user: Model) {
        val textViewName = itemView.findViewById(R.id.textViewUsername) as TextView
        val textViewAddress  = itemView.findViewById(R.id.textViewAddress) as TextView
        textViewName.text = user.mName
        textViewAddress.text = user.mAge
                itemView.setOnClickListener(this)

        }
        fun setOnCostomItemClickListener(itemClickListener:ItemClickListener){
                this.costomItemClickListener = itemClickListener
        }

        override fun onClick(p0: View?) {
                this.costomItemClickListener!!.onCostomItemClickListener(p0!!,adapterPosition)
        }

}
        }