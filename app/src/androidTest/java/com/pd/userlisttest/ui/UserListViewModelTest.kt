package com.pd.userlisttest.ui

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.pd.userlisttest.data.AppDatabase
import com.pd.userlisttest.data.entities.User
import com.pd.userlisttest.data.repository.UserRepository
import com.pd.userlisttest.getOrAwaitValue
import com.pd.userlisttest.ui.list.UserListViewModel
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class UserListViewModelTest : TestCase() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: UserListViewModel


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).allowMainThreadQueries().build()
        viewModel = UserListViewModel(context as Application)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    //It returns true because user added in DB
    @Test
    fun testAddingUser() {
        viewModel.addUser(User(0, "Pratik", "123"))
        val result = viewModel.allTodo.getOrAwaitValue().find {
            it.name == "Pratik" && it.number == "123"
        }
        assertThat(result != null).isTrue()
    }

    //Check user is not exists in DB
    @Test
    fun testCheckExistsUser() {
        viewModel.checkExistsUser(User(0, "Pratik123", "123"))
        val result = viewModel.isUserExists.getOrAwaitValue()
        assertThat(result).isEqualTo(false)
    }
}