package com.example.parcial1.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parcial1.data.QualificationDatabase
import com.example.parcial1.model.Activity
import com.example.parcial1.model.Subject

class SubjectViewModel (context: Context): ViewModel() {

    private val qualificationDatabase = QualificationDatabase(context)
    var subjects: MutableLiveData<List<Subject>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun saveSubject(subject: Subject): Boolean {
        return qualificationDatabase.saveSubject(subject)
    }

    fun saveActivity(activity: Activity, qualificationId: Int): Boolean {
        return qualificationDatabase.saveActivity(activity, qualificationId)
    }

    fun updateActivity(activity: Activity): Boolean {
        return qualificationDatabase.updateActivity(activity)
    }

    fun updateSubject(subject: Subject): Boolean {
        return qualificationDatabase.updateSubject(subject)
    }

    fun deleteActivity(activityCode: Int): Boolean {
        return qualificationDatabase.deleteActivity(activityCode)
    }
    fun deleteSubject(subjectCode: String): Boolean{
        return qualificationDatabase.deleteSubject(subjectCode)
    }

    private fun getSubjects(): ArrayList<Subject> {
        return qualificationDatabase.getSubjects()
    }

    fun refresh() {
        subjects.postValue(getSubjects())
        isLoading.value = true
    }

}