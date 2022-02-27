package com.atividades.atividade05.repository

import com.atividades.atividade05.data.UserDao
import com.atividades.atividade05.model.User
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepo @Inject constructor(private val userDao: UserDao) {

    //inserir detalhes do usuário para room
    suspend fun createUserRecords(user: User) : Long {
        return userDao.insertToRoomDatabase(user)
    }

    //obter detalhes de usuário único, por exemplo, com id 1
    val getUserDetails: Flow<List<User>> get() =  userDao.getUserDetails()

    //delete single user record
    suspend fun deleteSingleUserRecord() {
        userDao.deleteSingleUserDetails(1)
    }


}