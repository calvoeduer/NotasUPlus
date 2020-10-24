package com.example.parcial1.model

import androidx.annotation.IntRange
import com.google.gson.annotations.SerializedName
import java.io.Serializable

private  const val MAX_PERCENT= 1.0;
class Qualification : Serializable{
    @SerializedName("id")
    var id : Int = 0

    @IntRange(from = 1, to = 3)
    @SerializedName("cort")
    var cort: Int =1
    @SerializedName("activities")
    var activities : ArrayList<Activity> = ArrayList()
    @SerializedName("totalParcial")
    private val totalParcial: Float = 0.0F
    @SerializedName("total")
    val total: Float = 0.0F
    @SerializedName("totalPercent")
    private val totalPercent: Float = 0.0F
    @SerializedName("totalActivitiesPercent")
    var totalActivitiesPercent: Float = 0.0F






}




