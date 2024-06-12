package com.prueba.pruebatecnica.data.repository

import com.prueba.pruebatecnica.data.api.UserApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: UserApi) : UserRepository {
    override fun getUsers() = flow {
        val users = api.getUsers().results
        emit(users)
    }
}
