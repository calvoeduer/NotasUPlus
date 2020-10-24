package com.example.parcial1.network

import com.example.parcial1.model.Subject

interface ApiCallback<T> {
    fun onSuccess(result: T?)
    fun onFail(exception: Throwable)
}