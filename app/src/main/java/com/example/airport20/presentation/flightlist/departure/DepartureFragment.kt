package com.example.airport20.presentation.flightlist.departure


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController

import com.example.airport20.R
import com.example.airport20.domain.Departure
import com.example.airport20.domain.FlightType
import com.example.airport20.presentation.flighttabs.TabsFragmentDirections.actionTabsFragment2ToDetailsFragment
import kotlinx.android.synthetic.main.fragment_departure_list.*
import androidx.recyclerview.widget.DividerItemDecoration
import android.graphics.drawable.ClipDrawable.HORIZONTAL


class DepartureFragment : Fragment() {

    private val clickListener: DepartureClickListener = this::onFlightClicked

    private val recyclerViewAdapter =
        MyDepartureRecyclerViewAdapter(clickListener)

    private lateinit var viewModel: DepartureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_departure_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DepartureViewModel::class.java)
        viewModel.observableDepartureList.observe(this, Observer { departures ->
            departures?.let { recyclerViewAdapter.updateList(departures) } })

        departureList.adapter = recyclerViewAdapter
        val decoration = DividerItemDecoration(context, HORIZONTAL)
        departureList.addItemDecoration(decoration)
    }

    private fun onFlightClicked(item: Departure) {
        val flightDetails = actionTabsFragment2ToDetailsFragment()
        flightDetails.flightId = item.id
        flightDetails.flightType = FlightType.DEPARTURE.type
        view?.let {
            findNavController(it).navigate(flightDetails)
        }
    }
}