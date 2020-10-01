package com.example.pikachufinal.Pikachuapp.remoto

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pikachufinal.Pikachuapp.dao.PokemonDao
import com.example.pikachufinal.Pikachuapp.entities.TodosPoke

private const val DATA_BASE_NAME = "pokemones_database"
@Database(entities = [TodosPoke::class],version = 1, exportSchema = false)
abstract class PokeDataBase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {

        @Volatile
        private var INSTANCE: PokeDataBase? = null

        fun getDatabase(context: Context) : PokeDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    PokeDataBase::class.java, DATA_BASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}