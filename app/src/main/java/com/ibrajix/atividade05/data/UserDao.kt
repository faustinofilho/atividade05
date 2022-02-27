package com.atividades.atividade05.data

import androidx.room.*
import com.atividades.atividade05.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    /**
     * CREATE
     */

    //inserir dados no banco de dados da sala
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToRoomDatabase(user: User) : Long

    /**
     * READ
     */

    //obter todos os usuários inseridos no banco de dados da sala...normalmente isso deve ser uma lista de usuários
    @Transaction
    @Query("SELECT * FROM users_table ORDER BY id DESC")
    fun getUserDetails() : Flow<List<User>>

    //obter usuário único inserido no banco de dados da sala
    @Transaction
    @Query("SELECT * FROM users_table WHERE id = :id ORDER BY id DESC")
    fun getSingleUserDetails(id: Long) : Flow<User>

    /**
     * UPDATE
     */

    //atualizar detalhes do usuário
    @Update
    suspend fun updateUserDetails(user: User)

    /**
     * DELETE
     */

    //delete single user details
    @Query("DELETE FROM users_table WHERE id = :id")
    suspend fun deleteSingleUserDetails(id: Int)

    //excluir todos os detalhes do usuário
    @Delete
    suspend fun deleteAllUsersDetails(user: User)

}