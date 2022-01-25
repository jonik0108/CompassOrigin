package com.abbasov.compass.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.abbasov.compass.R
import com.abbasov.compass.adapter.DaysAdapter
import com.abbasov.compass.databinding.FragmentDaysofWeekBinding
import com.abbasov.compass.databinding.FragmentTeachersBinding
import com.abbasov.compass.models.DaysM
import com.abbasov.compass.models.Smth


class DaysofWeek : Fragment() {

    lateinit var binding: FragmentDaysofWeekBinding
    lateinit var daysAdapter: DaysAdapter
    var teach=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDaysofWeekBinding.inflate(LayoutInflater.from(context))
        teach=arguments?.get("teach").toString()
        val dayslist=ArrayList<DaysM>()

        dayslist.add(DaysM("Пн"))
        dayslist.add(DaysM("Вт"))
        dayslist.add(DaysM("Ср"))
        dayslist.add(DaysM("Чт"))
        dayslist.add(DaysM("Пт"))
        dayslist.add(DaysM("Сб"))
        daysAdapter= DaysAdapter(requireContext(),object :DaysAdapter.days{
            override fun teach(person: DaysM, position: Int) {
                val day=dayslist[position].days

                findNavController().navigate(R.id.timeofGroup, bundleOf("day" to day, "teach" to teach ))

            }
        },dayslist)
        binding.rv.adapter=daysAdapter
        return binding.root

    }



}