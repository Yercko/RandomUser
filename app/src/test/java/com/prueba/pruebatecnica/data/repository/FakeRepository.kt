package com.prueba.pruebatecnica.data.repository

import com.prueba.pruebatecnica.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeRepository : UserRepository {
    private val flow = MutableSharedFlow<List<User>>()
    suspend fun emit(value: List<User>) = flow.emit(value)
    override fun getUsers(): Flow<List<User>> = flow
}