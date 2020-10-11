package com.example.parcial1.view.adapter

import com.example.parcial1.model.Activity
import com.example.parcial1.model.Subject

interface SubjectListener {
    fun oneSubjectTap(subject: Subject , index: Int)
    fun onSubjectDeleteButtonTap(subject: Subject, position: Int)
    fun onUpdateSubjectButtontap(subject: Subject, index: Int)

}
