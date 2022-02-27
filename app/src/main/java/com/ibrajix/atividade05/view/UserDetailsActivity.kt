package com.atividades.atividade05.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.atividades.atividade05.R
import com.atividades.atividade05.databinding.ActivityMainBinding
import com.atividades.atividade05.databinding.ActivityUserDetailsBinding
import com.atividades.atividade05.viewmodel.DataStoreViewModel
import com.atividades.atividade05.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding

    private val userViewModel: UserViewModel by viewModels()
    private val dataStoreViewModel: DataStoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getUserDetails()

        handleClicks()

    }

    private fun handleClicks(){

        binding.btnClearRecord.setOnClickListener {

            //limpar registro do banco de dados do quarto
            userViewModel.doDeleteSingleUserRecord()

            //remover a chave de armazenamento de dados
            dataStoreViewModel.setSavedKey(false)

            //ir para a atividade principal
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }

    private fun getUserDetails(){

        this.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){

                userViewModel.doGetUserDetails()
                userViewModel.userDetails.collect { users->

                    for (user in users){
                        //set data into view
                        binding.txtName.text = user.name
                        binding.txtAge.text = user.age
                        binding.txtNumber.text = user.number
                    }

                }
            }
        }

    }

}