package com.example.kotlin.api

object RequestResponse {
    val vvhService = RetrofitUtlis.create(APIService::class.java, 1)

    val UomgService = RetrofitUtlis.create(APIService::class.java, 2)

    val weatherService = RetrofitUtlis.create(APIService::class.java,3)

    val huaoService = RetrofitUtlis.create(APIService::class.java,4)



}