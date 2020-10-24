package com.example.parcial1.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Subject( @SerializedName("code") var code: String, @SerializedName("name") var name: String) : Serializable {
    @SerializedName("qualifications")
    val qualifications : ArrayList<Qualification> = ArrayList()
    @SerializedName("definitive")
    val definitive : Float = 0.0F





}