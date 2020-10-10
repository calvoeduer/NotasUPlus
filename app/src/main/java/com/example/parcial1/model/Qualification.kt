package com.example.parcial1.model

import androidx.annotation.IntRange
private  const val MAX_PERCENT= 1.0;
class Qualification() {

    var id : Int = 0

    @IntRange(from = 1, to = 3)
    var cort: Int =1

    var activities : ArrayList<Activity> = ArrayList()

    val total: Float
        get() {
            return activities.map { a -> a.percent * a.note }.reduce { acc, fl -> acc + fl  }

        }
    private val totalPercent: Float
        get() {
            return activities.map { a -> a.percent }.reduce { acc, fl -> acc + fl }
        }
    fun addActivity(activity: Activity): Boolean {
        if (totalPercent + activity.percent > MAX_PERCENT)
            return false

        activities.add(activity)
        return true
    }



}