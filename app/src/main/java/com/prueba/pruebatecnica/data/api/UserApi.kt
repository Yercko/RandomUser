package com.prueba.pruebatecnica.data.api

import com.prueba.pruebatecnica.data.model.User
import com.prueba.pruebatecnica.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("api/")
    suspend fun getUsers(): UserResponse

}
