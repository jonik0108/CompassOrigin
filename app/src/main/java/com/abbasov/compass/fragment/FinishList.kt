package com.abbasov.compass.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.abbasov.compass.R
import com.abbasov.compass.adapter.FinishAdapter
import com.abbasov.compass.databinding.FragmentTimelistBinding
import kotlinx.android.synthetic.main.item_custom_dialog.view.*

import com.abbasov.compass.room.AppDatabase
import com.abbasov.compass.room.Person


class FinishList : Fragment() {
    lateinit var appDatabase: AppDatabase
    lateinit var binding:FragmentTimelistBinding
    lateinit var finishAdapter: FinishAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTimelistBinding.inflate(LayoutInflater.from(context))

        return binding.root
    }

    override fun onResume() {
        var arrayPerson=ArrayList<Person>()
        val day=arguments?.get("day")
        val teach=arguments?.get("teach")
        val times=arguments?.get("times")
        appDatabase = AppDatabase.getInstance(requireContext())
        arrayPerson.addAll(appDatabase.personDao().getAllPerson())
        val array=arrayPerson.filter { person ->
            person.teacher ==teach && person.week.toString().contains(day.toString()) && person.time==times
        }
        finishAdapter= FinishAdapter(requireContext(),object:FinishAdapter.finish{
            override fun teach(person: Person, position: Int) {
                val name=array[position].name.toString()
                val surname=array[position].surname.toString()
                val teach=arguments?.get("teach")
                val subject=array[position].subject.toString()
                val numberteach=array[position].numberteach.toString()
                val number=array[position].number.toString()
                findNavController().navigate(R.id.list, bundleOf("name" to name,"surname" to surname, "teach" to teach,
                    "subject" to subject, "numteach" to numberteach,"number" to number
                ))
            }
            override fun send(person: Person, position: Int) {
                val name=array[position].name.toString()
                val surname=array[position].surname.toString()
                val teach=arguments?.get("teach")
                val subject=array[position].subject.toString()
                val numberteach=array[position].numberteach.toString()
                val number=array[position].number.toString()
                findNavController().navigate(R.id.list, bundleOf("name" to name,"surname" to surname, "teach" to teach,
                "subject" to subject, "numteach" to numberteach,"number" to number
                    ))
            }
            override fun delete(person: Person, position: Int) {
                val dialog = AlertDialog.Builder(context!!)
                dialog.setTitle("вы точно хотите удалить?")
                dialog.setCancelable(false)
                dialog.setPositiveButton(
                    "Yes"
                ) { _, _ ->
                    appDatabase.personDao().deletePerson(person)
                    onResume()
                }
                dialog.setNeutralButton(
                    "No"
                ) { _, _ ->
                    onResume()
                }
                dialog.show()
            }
            override fun refactor(person: Person, position: Int) {
                val alertDialog = AlertDialog.Builder(context!!)
                val dialog = alertDialog.create()
                dialog.setCancelable(false)
                val dialogView = layoutInflater.inflate(R.layout.item_custom_dialog, null, false)
                dialog.setView(dialogView)
                dialogView.name.setText("${person.name}")
                dialogView.surname.setText("${person.surname}")
                dialogView.number.setText("${person.number}")
                dialogView.birth.setText("${person.birth}")
                dialogView.time.setText("${person.time}")

                dialogView.save.setOnClickListener {
                    person.name=dialogView.name.text.toString()
                    person.surname=dialogView.surname.text.toString()
                    person.number=dialogView.number.text.toString()
                    person.birth=dialogView.birth.text.toString()
                    person.time=dialogView.time.text.toString()
                    val teachers=dialogView.spinner_teachers.selectedItem.toString()
                    val week=dialogView.spinner_week.selectedItem.toString()
                    val numberteacher=dialogView.numberteachers.selectedItem.toString()
                    val subject=dialogView.subject.selectedItem.toString()
                    Toast.makeText(context, "$teachers$week$numberteacher$subject", Toast.LENGTH_SHORT).show()
                    person.teacher=teachers
                    person.week=week
                    person.numberteach=numberteacher
                    person.subject=subject
                    appDatabase.personDao().editPerson(person)
                    dialog.dismiss()
                    onResume()

                }
                dialog.show()

            }
        },array)
        binding.rv.adapter=finishAdapter
        super.onResume()
    }


}