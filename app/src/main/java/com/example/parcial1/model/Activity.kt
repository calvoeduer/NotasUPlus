package com.example.parcial1.model

import androidx.annotation.FloatRange
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Activity : Serializable{
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String = ""

    @FloatRange(from = 0.0, to = 5.0)
    @SerializedName("note")
    var note: Float = 0.0F
    @FloatRange(from = 0.0, to = 1.0)
    @SerializedName("percent")
    var percent: Float = 0.0F
    @SerializedName("value")
    var value: Float = 0.0F

    @SerializedName("qualificationId")
    var qualificationId: Int = 0


}