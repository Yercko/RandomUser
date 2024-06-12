package com.prueba.pruebatecnica.data.repository

import com.prueba.pruebatecnica.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>
}