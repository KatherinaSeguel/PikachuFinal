package com.example.pikachufinal.Pikachuapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pikachufinal.Pikachuapp.entities.TodosPoke

interface PokemonDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertAllPokemon(mPokemon: List<TodosPoke>)

 @Query("SELECT * FROM pokemon_table")
 fun getAllPokemonFromBD(): LiveData<List<TodosPoke>>


  @Query("SELECT * FROM pokemon_table WHERE pokemon =: id")
  fun getPokemonByID(id: String ): LiveData<TodosPoke>
}