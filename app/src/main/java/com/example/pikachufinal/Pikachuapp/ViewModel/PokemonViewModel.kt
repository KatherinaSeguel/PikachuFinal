package com.example.pikachufinal.Pikachuapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pikachufinal.Pikachuapp.Caracterist.CharacteristicDao
import com.example.pikachufinal.Pikachuapp.entities.TodosPoke
import com.example.pikachufinal.Pikachuapp.local.PokemonRepository
import com.example.pikachufinal.Pikachuapp.remoto.PokeDataBase
import kotlinx.coroutines.launch

class PokemonViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: PokemonRepository

    init {
        val pokemonDao = PokeDataBase.getDatabase(application).pokemonDao()
        repository = PokemonRepository(pokemonDao)
        repository.getDataFromServer()


    }

    fun exposeLiveDataFromDatabase(): LiveData<List<TodosPoke>> {
        return repository.mLiveData
    }

    fun obtainPokemonByID(id: String): LiveData<TodosPoke> {
        return repository.obtainPokemoninByID(id)
    }

    fun updatePokemon(todosPoke: TodosPoke)= viewModelScope.launch {
        repository.updatePokemon(todosPoke)
    }
}



