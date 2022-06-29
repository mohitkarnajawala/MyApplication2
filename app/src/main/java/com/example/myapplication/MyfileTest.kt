package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MyfileTest:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //var university=University()
    }
}



/*
class University(private val repository: Repository<Student>) {
    fun getPaidCoursesWithTheNumbersOfSubscribedStudents(coursesCount: Int): Map<Course, Int> =repository.get()
        .flatMap { it.subscribedCourses }
        .asSequence()
        .filter { it.isPaid }
        .groupBy { it }
        .mapValues { map-> map.value.size }
        .filter { it.value>0 }
        .toList()
        .sortedByDescending { (_, value) -> value}
        .take(coursesCount)
        .toMap()


}
typealias Id = Int
data class Student(val id: Id, val name: String, val subscribedCourses: List<Course>)

data class Course(val id: Id, val name: String, val isPaid: Boolean)

interface Repository<T> {
    fun get(): Iterable<T>
}
*/
