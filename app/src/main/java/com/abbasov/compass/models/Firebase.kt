package com.abbasov.compass.models

class Firebase {
    var birth:String? = null
    var id:Int? = null
    var name:String? = null
    var number:String? = null
    var numberteach:String?=null
    var subject:String?=null
    var surname:String? = null
    var teacher:String? = null
    var time:String? = null
    var week:String? = null

    constructor(
        birth: String?,
        id: Int?,
        name: String?,
        number: String?,
        numberteach: String?,
        subject: String?,
        surname: String?,
        teacher: String?,
        time: String?,
        week: String?
    ) {
        this.birth = birth
        this.id = id
        this.name = name
        this.number = number
        this.numberteach = numberteach
        this.subject = subject
        this.surname = surname
        this.teacher = teacher
        this.time = time
        this.week = week
    }

    override fun toString(): String {
        return "Firebase(birth=$birth, id=$id, name=$name, number=$number, numberteach=$numberteach, subject=$subject, surname=$surname, teacher=$teacher, time=$time, week=$week)"
    }

}