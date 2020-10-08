package com.example.pikachufinal.Pikachuapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pikachufinal.Pikachuapp.entities.TodosPoke
@Dao
interface PokemonDao {




 @Query("SELECT * FROM pokemon_table")
 fun getAllPokemonFromBD(): LiveData<List<TodosPoke>>


  @Query("SELECT * FROM pokemon_table WHERE pokemon =:id")
  fun getPokemonByID(id: String ): LiveData<TodosPoke>

 @Query("SELECT count(*) from pokemon_table WHERE pokemon =:id")
 fun Pokemonactual(id: String): Int

 @Insert(onConflict = OnConflictStrategy.REPLACE)
 suspend fun insertAllPokemon(mPokemon: TodosPoke)

 @Insert(onConflict = OnConflictStrategy.REPLACE)
 fun MuchosPokemon(pokemones: List<TodosPoke>)
 @Update
 suspend fun updatePokemon(poke: TodosPoke)
}