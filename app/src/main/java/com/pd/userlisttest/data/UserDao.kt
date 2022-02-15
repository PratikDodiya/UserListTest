package com.pd.userlisttest.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pd.userlisttest.data.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>

    //Annotate the 'insert' function below. Set the onConflict strategy to REPLACE so if exactly the same user exists, it will just replace it.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Query("SELECT EXISTS(SELECT * FROM user WHERE name=(:name) AND number=(:number))")
    suspend fun checkExistsUser(name: String, number: String): Boolean
}