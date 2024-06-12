package com.prueba.pruebatecnica.ui.userlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.prueba.pruebatecnica.data.model.Coordinates
import com.prueba.pruebatecnica.data.model.DateOfBirth
import com.prueba.pruebatecnica.data.model.ID
import com.prueba.pruebatecnica.data.model.Location
import com.prueba.pruebatecnica.data.model.Login
import com.prueba.pruebatecnica.data.model.Name
import com.prueba.pruebatecnica.data.model.Picture
import com.prueba.pruebatecnica.data.model.Registered
import com.prueba.pruebatecnica.data.model.Street
import com.prueba.pruebatecnica.data.model.Timezone
import com.prueba.pruebatecnica.data.model.User
import com.prueba.pruebatecnica.data.repository.FakeRepository
import com.prueba.pruebatecnica.data.repository.MainCoroutineRule
import com.prueba.pruebatecnica.data.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class UserListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: UserListViewModel
    private val userRepository: UserRepository = mock()
    private val fakeRepository = FakeRepository()

    private val mockUser = User(
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

    @Before
    fun setup() {
        viewModel = UserListViewModel(userRepository)
    }

    @Test
    fun `when getUsers is called, then users live data is updated`() = mainCoroutineRule.runBlockingTest {
        val users = listOf(mockUser)
        whenever(userRepository.getUsers()).thenReturn(flowOf(users))

        viewModel = UserListViewModel(userRepository)
        val firstValue = viewModel.users.first()

        assertEquals(users, firstValue)
    }

    @Test
    fun `when selectUser is called, then selectedUser live data is updated`() = mainCoroutineRule.runBlockingTest {
        viewModel.selectUser(mockUser)
        assertEquals(mockUser, viewModel.selectedUser.value)
    }
}