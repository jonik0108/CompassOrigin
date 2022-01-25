package com.abbasov.compass.fragment

import android.R.attr.data
import android.R.attr.phoneNumber
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.abbasov.compass.databinding.FragmentListBinding
import java.text.SimpleDateFormat
import java.util.*


class List : Fragment() {

    lateinit var binding: FragmentListBinding
    var name=""
    var surname=""
    var subject=""
    var date=""
    var teach=""
    var hw=""
    var xulq=""
    var faol=""
    var numberteach=""
    var textuz=""
    var textru=""
    var a=0
    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(LayoutInflater.from(context))

        binding.refresh.setOnClickListener {
            onResume()
        }

        val surname1=arguments?.get("name").toString()
        val name1=arguments?.get("surname").toString()
        val number=arguments?.get("number").toString()
        binding.txtnumb.text="$number\n$surname1\n$name1"

            binding.send.setOnClickListener {
                if (a==0) {
                    a++
                    val sms = SmsManager.getDefault()
                    val parts = sms.divideMessage(textuz)
                    sms.sendMultipartTextMessage(number, null, parts, null, null)
                    Toast.makeText(context, "send", Toast.LENGTH_SHORT).show()
                    binding.count.text = a.toString()
                    binding.hw.text = null
                    binding.faol.text = null
                    binding.xulq.text = null
                }else{
                    Toast.makeText(context, "Вы уже отправили сообщения", Toast.LENGTH_SHORT).show()
                }
            }
        binding.data.setOnClickListener {
            val datePickerDialog = context?.let { it1 -> DatePickerDialog(it1) }

            datePickerDialog?.setOnDateSetListener { view, year, month, dayOfMonth ->
                if (dayOfMonth<10 && (month+1)<10){

                    date="0${dayOfMonth}.0${month+1}.$year"

                }else if (dayOfMonth<10){
                    date="0${dayOfMonth}.${month+1}.$year"
                }else if ((month+1)<10){
                    date="${dayOfMonth}.0${month+1}.$year"

                }else {
                    date="${dayOfMonth}.${month+1}.$year"

                }

            }

            datePickerDialog?.show()
        }
        binding.data2.setOnClickListener {
            date= SimpleDateFormat("dd.MM.yyyy").format(Date())
        }


            binding.sendru.setOnClickListener {
                if (a==0) {
                    a++
                    val sms = SmsManager.getDefault()
                    val parts = sms.divideMessage(textru)
                    sms.sendMultipartTextMessage(number, null, parts, null, null)
                    Toast.makeText(context, "send", Toast.LENGTH_SHORT).show()
                    binding.count.text = a.toString()
                    binding.hw.text = null
                    binding.faol.text = null
                    binding.xulq.text = null
                }
                else{
                    Toast.makeText(context, "Вы уже отправили сообщения", Toast.LENGTH_SHORT).show()
                }
            }

        return binding.root

    }

    @SuppressLint("SimpleDateFormat")
    override fun onResume() {
        surname=arguments?.get("name").toString()
        name=arguments?.get("surname").toString()
        subject=arguments?.get("subject").toString()
        teach=arguments?.get("teach").toString()
        hw=binding.hw.text.toString()
        xulq=binding.xulq.text.toString()
        faol=binding.faol.text.toString()
        numberteach=arguments?.get("numteach").toString()
        textuz="$surname $name - укув марказидаги $subject укиши хакида маълумот:\nМашгулот санаси: $date й\nУй вазифа учун бахо: $hw\nФаоллик учун бахо: $faol\nХулки: $xulq\nУкитувчи: $numberteach\nМаълумот учун: 33 730 70 05\nХурмат билан COMPASS"
        textru="$surname $name - успеваемость в учебном центре по предмету $subject:\nДата занятия: $date г\nОценка за домашнее задание: $hw\nОценка за активность: $faol\nПоведение: $xulq\nПреподаватель: " +
                "$numberteach\nДля справок: 33 730 70 05\nС уважением COMPASS"
        binding.txtuz.text= textuz
        binding.txtru.text=textru
        super.onResume()
    }
}