package com.example.pikachufinal.Pikachuapp.Caracterist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacteristicDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertAllCharac(mCharacteristic: List<Characteristic>)

    @Query("SELECT*FROM caracteristicas_table")
    fun getAllCaractFromBD(): LiveData<List<Characteristic>>

@Query("SELECT*FROM caracteristicas_table WHERE caracter =:id")
fun getCaractByID(id: String): LiveData<Characteristic>
}