package com.rao.rxjava

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.rao.rxjava.CityDesc

import java.util.ArrayList


class CarDetailsAdapter(private val mContext: Context) : RecyclerView.Adapter<CarDetailsAdapter.CarRepoViewHolder>() {

    private val gitHubRepos = ArrayList<CityDesc>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarRepoViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.album_card, parent, false)
        return CarRepoViewHolder(view)

    }

    override fun onBindViewHolder(holder: CarRepoViewHolder, position: Int) {
        holder.carname.text = gitHubRepos[position].zipcode
        holder.people.text = "" + gitHubRepos[position].peopleRange + " people"
        holder.money.text = "" + gitHubRepos[position].currency
        if (gitHubRepos[position].isDevelopingCountry) {
            holder.image.setImageResource(android.R.drawable.ic_delete)
            holder.image.visibility = View.VISIBLE
        }
        holder.carimage.setImageResource(R.drawable.car_ic_drive_placeholder)

    }

    fun setGitHubRepos(repos: List<CityDesc>?) {
        if (repos == null) {
            return
        }
        gitHubRepos.clear()
        gitHubRepos.addAll(repos)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return gitHubRepos.size
    }

    inner class CarRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
            Toast.makeText(mContext, gitHubRepos.get(layoutPosition).peopleRange, Toast.LENGTH_SHORT).show()
        }

        internal var carname: TextView
        internal var people: TextView
        internal var money: TextView
        internal var time: TextView
        internal var image: ImageView
        internal var carimage: ImageView

        init {
            carname = itemView.findViewById<View>(R.id.car_name) as TextView
            people = itemView.findViewById<View>(R.id.people) as TextView
            money = itemView.findViewById<View>(R.id.money) as TextView
            time = itemView.findViewById<View>(R.id.time) as TextView
            image = itemView.findViewById<View>(R.id.meter) as ImageView
            carimage = itemView.findViewById<View>(R.id.img1) as ImageView
            itemView.setOnClickListener(this)
        }
    }
}
