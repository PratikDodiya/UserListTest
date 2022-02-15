package com.pd.userlisttest.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pd.userlisttest.data.AppDatabase
import com.pd.userlisttest.data.entities.User
import com.pd.userlisttest.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel(application: Application) : AndroidViewModel(application) {

    val allTodo: LiveData<List<User>>
    private val repository: UserRepository
    val isUserExists = MutableLiveData<Boolean>()

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        allTodo = repository.readAllUsers
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun checkExistsUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            isUserExists.postValue(repository.checkExistsUser(user))
        }
    }
}