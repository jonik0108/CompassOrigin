package com.abbasov.compass.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.abbasov.compass.R
import com.abbasov.compass.adapter.TeachersAdapter
import com.abbasov.compass.databinding.FragmentTeachersBinding
import com.abbasov.compass.models.TeacherM
import com.abbasov.compass.room.AppDatabase
import com.abbasov.compass.room.Person


class Teachers : Fragment() {

    lateinit var binding: FragmentTeachersBinding
    lateinit var teachersAdapter: TeachersAdapter
    lateinit var appDatabase: AppDatabase
    val list=ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{


        appDatabase = AppDatabase.getInstance(requireContext())
        val arrayJustin=ArrayList<Person>()
        arrayJustin.addAll(appDatabase.personDao().getAllPerson())
        val array1=arrayJustin.filter { person ->
            person.teacher =="Mr.Justin"
        }
        val arrayDiyora=ArrayList<Person>()
        arrayDiyora.addAll(appDatabase.personDao().getAllPerson())
        val array2=arrayDiyora.filter { person ->
            person.teacher =="Ms.Diyora"
        }
        val arraySabina=ArrayList<Person>()
        arraySabina.addAll(appDatabase.personDao().getAllPerson())
        val array3=arraySabina.filter { person ->
            person.teacher =="Ms.Sabina"
        }
        val arrayDori=ArrayList<Person>()
        arrayDori.addAll(appDatabase.personDao().getAllPerson())
        val array4=arrayDori.filter { person ->
            person.teacher =="Ms.Dori"
        }
        val arrayJanona=ArrayList<Person>()
        arrayJanona.addAll(appDatabase.personDao().getAllPerson())
        val array5=arrayJanona.filter { person ->
            person.teacher =="Ms.Janona"
        }
        val arrayHenry=ArrayList<Person>()
        arrayHenry.addAll(appDatabase.personDao().getAllPerson())
        val array6=arrayHenry.filter { person ->
            person.teacher =="Mr.Henry"
        }
        val arrayJames=ArrayList<Person>()
        arrayJames.addAll(appDatabase.personDao().getAllPerson())
        val array7=arrayJames.filter { person ->
            person.teacher =="Mr.James"
        }
        val arrayMerle=ArrayList<Person>()
        arrayMerle.addAll(appDatabase.personDao().getAllPerson())
        val array8=arrayMerle.filter { person ->
            person.teacher =="Mr.Merle"
        }
        val arrayKevin=ArrayList<Person>()
        arrayKevin.addAll(appDatabase.personDao().getAllPerson())
        val array9=arrayKevin.filter{ person ->
            person.teacher == "Mr.Kevin"
        }


        binding = FragmentTeachersBinding.inflate(LayoutInflater.from(context))
        val listTeacher=ArrayList<TeacherM>()
        listTeacher.add(TeacherM("Mr.Justin","${array1.size}"))
        listTeacher.add(TeacherM("Ms.Diyora","${array2.size}"))
        listTeacher.add(TeacherM("Ms.Sabina","${array3.size}"))
        listTeacher.add(TeacherM("Ms.Dori","${array4.size}"))
        listTeacher.add(TeacherM("Ms.Janona","${array5.size}"))
        listTeacher.add(TeacherM("Mr.Henry","${array6.size}"))
        listTeacher.add(TeacherM("Mr.James","${array7.size}"))
        listTeacher.add(TeacherM("Mr.Merle","${array8.size}"))
        listTeacher.add(TeacherM("Mr.Kevin","${array9.size}"))


        teachersAdapter= TeachersAdapter(requireContext(), object:TeachersAdapter.teach{
            override fun teach(person: TeacherM, position: Int) {
                val bundle=listTeacher[position].name
                findNavController().navigate(R.id.daysofWeek, bundleOf("teach" to bundle))
            }
        },listTeacher)
        binding.rv.adapter=teachersAdapter

        return binding.root
    }


}