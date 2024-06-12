package com.prueba.pruebatecnica.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.prueba.pruebatecnica.data.api.UserApi
import com.prueba.pruebatecnica.data.model.Coordinates
import com.prueba.pruebatecnica.data.model.DateOfBirth
import com.prueba.pruebatecnica.data.model.ID
import com.prueba.pruebatecnica.data.model.Info
import com.prueba.pruebatecnica.data.model.Location
import com.prueba.pruebatecnica.data.model.Login
import com.prueba.pruebatecnica.data.model.Name
import com.prueba.pruebatecnica.data.model.Picture
import com.prueba.pruebatecnica.data.model.Registered
import com.prueba.pruebatecnica.data.model.Street
import com.prueba.pruebatecnica.data.model.Timezone
import com.prueba.pruebatecnica.data.model.User
import com.prueba.pruebatecnica.data.model.UserResponse
import com.prueba.pruebatecnica.ui.userlist.UserListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    private lateinit var repository: UserRepository
    private val api: UserApi = mock()

    @Before
    fun setUp() = runTest {
        val mockUser = User(
            gender = "male",
            name = Name("Mr", "John", "Doe"),
            location = Location(
                street = Street("0552 NW", "Valwood Pkwy"),
                city = "Wollongong",
                state = "NSW",
                country = "Australia",
                postcode = "2500",
                coordinates = Coordinates("-81.6412", "-30.7756"),
                timezone = Timezone("-1:00", "Azores, Cape Verde Islands")
            ),
            email = "john.doe@example.com",
            login = Login("uuid", "username", "password", "salt", "md5", "sha1", "sha256"),
            dob = DateOfBirth("1980-01-01T00:00:00Z", 41),
            registered = Registered("2005-04-15T00:00:00Z", 16),
            phone = "123-456-7890",
            cell = "098-765-4321",
            id = ID("ID", "12345"),
            picture = Picture("large.jpg", "medium.jpg", "thumbnail.jpg"),
            nat = "AU"
        )
        `when`(api.getUsers()).thenReturn(UserResponse(listOf(mockUser), Info("seed", 1, 1, "1.0")))
        repository = UserRepositoryImpl(api)
    }

    @Test
    fun `should fetch users from API`() = runTest {
        val users = repository.getUsers().first()
        assertEquals(1, users.size)
        assertEquals("John", users[0].name.first)
        assertEquals("Doe", users[0].name.last)
    }
}