package com.abbasov.compass.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.abbasov.compass.databinding.FragmentAddPeopleBinding
import com.abbasov.compass.room.AppDatabase
import com.abbasov.compass.room.Person


class AddPeople : Fragment() {
    lateinit var appDatabase: AppDatabase
    lateinit var binding: FragmentAddPeopleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddPeopleBinding.inflate(LayoutInflater.from(context))
        appDatabase = AppDatabase.getInstance(requireContext())
        binding.save.setOnClickListener {
            val teachers=binding.spinnerTeachers.selectedItem.toString()
            val weekday=binding.spinnerWeek.selectedItem.toString()
            val numberteach=binding.numberteachers.selectedItem.toString()
            val subject=binding.subject.selectedItem.toString()
            val person= Person()
            person.name=binding.name.text.toString()
            person.surname=binding.surname.text.toString()
            val num="+998"+binding.number.text.toString()
            person.number=num
            person.birth=binding.birth.text.toString()
            person.teacher=teachers
            person.time=binding.time.text.toString()
            person.week=weekday
            person.numberteach=numberteach
            person.subject=subject
            if (binding.name.toString()=="" || binding.surname.toString()==""||binding.number.toString()==""||binding.birth.toString()==""||binding.time.toString()==""){
                Toast.makeText(context, "не все поля заполнены", Toast.LENGTH_SHORT).show()
            }else{
                appDatabase.personDao().addPerson(person)
                Toast.makeText(context, "save", Toast.LENGTH_SHORT).show()
                binding.name.text=null
                binding.surname.text=null
                binding.number.text=null
                binding.birth.text=null
                binding.time.text=null
                onResume()
            }

        }
        return binding.root
    }

}