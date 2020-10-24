package com.example.parcial1.network

import com.example.parcial1.model.Activity
import com.example.parcial1.model.Subject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QualificationsService {
    private val retrofit : NotasUApi = ServiceBuilder.buidService(NotasUApi::class.java)
    fun getSubjects(apiCallback: ApiCallback<List<Subject>>){

        retrofit.getSubjects().enqueue(
            object : retrofit2.Callback<List<Subject>>{
                override fun onFailure(call : Call<List<Subject>>, t : Throwable){
                    apiCallback.onFail(t)
                }

                override fun onResponse(
                    call: Call<List<Subject>> ,
                    response: Response<List<Subject>>
                ){
                    val response = response.body()
                    apiCallback.onSuccess(response)

                }
            }
        )
    }

    fun saveSubjects(subject: Subject, apiCallback: ApiCallback<Subject>){
        retrofit.saveSubject(subject).enqueue(
            object : Callback<Subject>{
                override fun onFailure(call: Call<Subject>, t: Throwable) {
                    apiCallback.onFail(t)
                }

                override fun onResponse(call: Call<Subject>, response: Response<Subject>) {
                    val response = response.body()
                }
            }
        )
    }

    fun updateSubject(subject: Subject, apiCallback: ApiCallback<Subject>){
        retrofit.updateSubject(subject.code, subject).enqueue(
            object : Callback<Subject>{
                override fun onResponse(call: Call<Subject>, response: Response<Subject>) {
                    apiCallback.onSuccess(response.body())
                }

                override fun onFailure(call: Call<Subject>, t: Throwable) {
                    apiCallback.onFail(t)
                }

            }
        )
    }

    fun saveActivity(activity: Activity, apiCallback: ApiCallback<Activity>){
        retrofit.saveActivity(activity).enqueue(
            object : Callback<Activity>{

                override fun onFailure(call: Call<Activity>, t: Throwable) {
                    apiCallback.onFail(t)
                }
                override fun onResponse(call: Call<Activity>, response: Response<Activity>) {
                    val  response = response.body()
                    apiCallback.onSuccess(response)
                }



            }
        )

    }

    fun updateActivity(activity: Activity, apiCallback: ApiCallback<Activity>){
        retrofit.updateActivity(activity.id, activity).enqueue(
            object : Callback<Activity>{
                override fun onFailure(call: Call<Activity>, t: Throwable) {
                    apiCallback.onFail(t)
                }

                override fun onResponse(call: Call<Activity>, response: Response<Activity>) {
                    apiCallback.onSuccess(response.body())
                }

            }
        )
    }


    fun deleteSubject(subjectCode: String, apiCallback: ApiCallback<Subject>){
        retrofit.deleteSubject(subjectCode).enqueue(
            object : Callback<Subject>{
                override fun onResponse(call: Call<Subject>, response: Response<Subject>) {
                    apiCallback.onSuccess(response.body())
                }

                override fun onFailure(call: Call<Subject>, t: Throwable) {
                    apiCallback.onFail(t)
                }

            }
        )
    }

    fun deleteActivity(activityId: Int, apiCallback: ApiCallback<Activity>){
        retrofit.deleteActivity(activityId).enqueue(
            object : Callback<Activity>{
                override fun onResponse(call: Call<Activity>, response: Response<Activity>) {
                    apiCallback.onSuccess(response.body())
                }

                override fun onFailure(call: Call<Activity>, t: Throwable) {
                    apiCallback.onFail(t)
                }

            }
        )
    }


}