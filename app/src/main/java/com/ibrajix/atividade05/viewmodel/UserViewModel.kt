package com.atividades.atividade05.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.atividades.atividade05.model.User
import com.atividades.atividade05.repository.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel @ViewModelInject constructor(private val userRepo: UserRepo): ViewModel() {


    /**
     * Inserir detalhes do usuário
     */
    private val _response = MutableLiveData<Long>()
    val response: LiveData<Long> = _response

    //inserir detalhes do usuário no banco de dados da sala
    fun insertUserDetails(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            _response.postValue(userRepo.createUserRecords(user))
        }
    }

    /**
     * Recuperar detalhes do usuário
     */
    //verifique se a música é curtida
    private val _userDetails = MutableStateFlow<List<User>>(emptyList())
    val userDetails : StateFlow<List<User>> =  _userDetails

    fun doGetUserDetails(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.getUserDetails
                .catch { e->
                    //Erro de registro aqui
                }
                .collect {
                    _userDetails.value = it
                }
        }
    }

    /**
     * Excluir registro de usuário único
     */
    fun doDeleteSingleUserRecord(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.deleteSingleUserRecord()
        }
    }

}