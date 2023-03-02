package com.example.kotlin.http

interface IGetDataListener<T> {
    fun onSuccess(dataobj: T)
    fun onFailure(reasonOBJ: Any)
}
