package com.example.parcial1.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parcial1.data.QualificationDatabase
import com.example.parcial1.model.Activity
import com.example.parcial1.model.Subject
import com.example.parcial1.network.ApiCallback
import com.example.parcial1.network.QualificationsService

class SubjectViewModel (context: Context): ViewModel() {

    private val qualificationService = QualificationsService()
    var subjects: MutableLiveData<List<Subject>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun saveSubject(subject: Subject, apiCallback: ApiCallback<Subject>) {
      qualificationService.saveSubjects(subject, apiCallback)
    }

    fun saveActivity(activity: Activity, apiCallback: ApiCallback<Activity>) {
        qualificationService.saveActivity(activity, apiCallback)
    }

    fun updateActivity(activity: Activity, apiCallback: ApiCallback<Activity>) {
       qualificationService.updateActivity(activity, apiCallback)
    }

    fun updateSubject(subject: Subject, apiCallback: ApiCallback<Subject>) {
       qualificationService.updateSubject(subject, apiCallback)
    }

    fun deleteActivity(activityCode: Int, apiCallback: ApiCallback<Activity>){
        qualificationService.deleteActivity(activityCode, apiCallback)
    }
    fun deleteSubject(subjectCode: String, apiCallback: ApiCallback<Subject>){
        qualificationService.deleteSubject(subjectCode, apiCallback)
    }

    private fun getSubjects() {
        qualificationService.getSubjects(object : ApiCallback<List<Subject>>{
            override fun onSuccess(result: List<Subject>?) {
                subjects.postValue(result)
                isLoading.value = true
            }

            override fun onFail(exception: Throwable) {
                isLoading.value = true
            }
        })
    }

    fun refresh() {
        getSubjects()
    }

}