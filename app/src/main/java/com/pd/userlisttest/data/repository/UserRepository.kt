package com.pd.userlisttest.data.repository

import androidx.lifecycle.LiveData
import com.pd.userlisttest.data.UserDao
import com.pd.userlisttest.data.entities.User

class UserRepository(private val userDao: UserDao) {

    val readAllUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun checkExistsUser(user: User): Boolean = userDao.checkExistsUser(user.name, user.number)

}