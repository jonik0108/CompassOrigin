package com.abbasov.compass.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Person {
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    @ColumnInfo(name="person")
    var name:String? = null
    var surname:String? = null
    var number:String? = null
    var birth:String? = null
    var teacher:String? = null
    var subject:String?=null
    var time:String? = null
    var week:String? = null
    var numberteach:String?=null


}