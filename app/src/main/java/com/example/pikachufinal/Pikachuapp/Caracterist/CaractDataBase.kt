package com.example.pikachufinal.Pikachuapp.Caracterist

import android.content.Context
import android.os.strictmode.InstanceCountViolation
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


private const val DATA_BASE_NAME = "caracteristicas_database"

@Database(entities = [Characteristic::class],version = 1,exportSchema = false)

abstract class  CaractDataBase : RoomDatabase() {
    abstract  fun characteristicDao(): CharacteristicDao

    companion object {

        @Volatile
        private var INSTANCE: CaractDataBase? = null
        fun getDatabase(context: Context) : CaractDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    CaractDataBase::class.java, DATA_BASE_NAME
                ).build()
                INSTANCE = instance
                return  instance

            }
        }
    }


}