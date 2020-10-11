package com.example.parcial1.model

import androidx.annotation.IntRange
import java.io.Serializable

private  const val MAX_PERCENT= 1.0;
class Qualification : Serializable{

    var id : Int = 0

    @IntRange(from = 1, to = 3)
    var cort: Int =1

    var activities : ArrayList<Activity> = ArrayList()

    private val totalParcial: Float
        get() {
            if (activities.size == 0) {
                return 0.0F
            }
            return activities.map { a -> a.percent * a.note }.reduce { acc, fl -> acc + fl }
        }

    val total: Float
        get() {
            return totalParcial * totalPercent
        }
    private val totalPercent: Float
        get() {
            return when (cort) {
                1 -> 0.3F
                2 -> 0.3F
                3 -> 0.4F
                else -> 0.0F
            }
        }

    val totalActivitiesPercent: Float
        get() {
            if (activities.size == 0) {
                return 0.0F
            }
            return activities.map { a -> a.percent }.reduce { acc, fl -> acc + fl }
        }


    fun addActivity(activity: Activity): Boolean {
        if (totalActivitiesPercent + activity.percent > MAX_PERCENT)
            return false

        activities.add(activity)
        return true
    }


}




