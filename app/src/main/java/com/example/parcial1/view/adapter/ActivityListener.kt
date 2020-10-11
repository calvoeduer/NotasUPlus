package com.example.parcial1.view.adapter

import com.example.parcial1.model.Activity

interface ActivityListener {
    fun onActivityTap(activity: Activity, position: Int)
    fun onActivityDeleteButtonTap(activity: Activity, position: Int)
    fun onUpdateActivityButtonTap(activity: Activity, position: Int)
}