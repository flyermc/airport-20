package com.example.airport20.presentation.flightlist.arrival

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import com.example.airport20.R
import com.example.airport20.domain.Arrival
import com.example.airport20.domain.FlightManager
import com.example.airport20.dummy.DummyContent


import com.example.airport20.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_arrival.view.*

typealias ClickListener = (Arrival) -> Unit

class MyArrivalRecyclerViewAdapter(
    private val clickListener: ClickListener
) : RecyclerView.Adapter<MyArrivalRecyclerViewAdapter.ViewHolder>() {

    private var flightList: List<Arrival> = emptyList<Arrival>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_arrival, parent, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener {
            clickListener(flightList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = flightList[position]
        holder.mIdView.text = item.id.toString()
        holder.mContentView.text = item.city
    }

    fun updateList(flightList: List<Arrival>) {
        this.flightList = flightList
    }

    override fun getItemCount(): Int = flightList.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}