package com.abbasov.compass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.abbasov.compass.adapter.TimeAdapter
import com.abbasov.compass.databinding.FragmentTimeofGroupBinding
import com.abbasov.compass.models.TimesM
import com.abbasov.compass.room.AppDatabase
import com.abbasov.compass.room.Person


class TimeofGroup : Fragment() {

    lateinit var binding:FragmentTimeofGroupBinding
    lateinit var timeAdapter: TimeAdapter
    lateinit var appDatabase: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTimeofGroupBinding.inflate(LayoutInflater.from(context))
        appDatabase = AppDatabase.getInstance(requireContext())

        var arraytime2=ArrayList<String>()
        val day=arguments?.get("day")
        val teach=arguments?.get("teach")
        val arrayPerson=ArrayList<Person>()
        arrayPerson.addAll(appDatabase.personDao().getAllPerson())
        val arraytime=ArrayList<TimesM>()
        val time=ArrayList<TimesM>()


        for (i in arrayPerson) {
            if (i.teacher==teach){
                if ((i.week.toString()).contains(day.toString())){
                    arraytime.add(TimesM(i.time.toString()))
                }
            }
        }
        for (i in arraytime) {
            arraytime2.add(i.times)
        }
        val set: Set<String> = HashSet(arraytime2)
        arraytime2.clear()
        arraytime2.addAll(set)
        for (s in arraytime2) {
            time.add(TimesM(s))
        }


        timeAdapter= TimeAdapter(requireContext(),object : TimeAdapter.time{
            override fun teach(timesM: TimesM, position: Int) {
                val time1=time[position].times
                findNavController().navigate(R.id.timelist, bundleOf("day" to day, "teach" to teach, "times" to time1))

            }
        },time)
        binding.rv.adapter=timeAdapter




        return binding.root
    }


}