package com.example.myapplication.common

import androidx.room.TypeConverter
import java.util.*

class Converter {

    @TypeConverter
    fun fromDateToLong(value:Date):Long{
        return  value.time
    }

    @TypeConverter
    fun fromLongToDate(value:Long):Date{
        return Date(value)
    }
}