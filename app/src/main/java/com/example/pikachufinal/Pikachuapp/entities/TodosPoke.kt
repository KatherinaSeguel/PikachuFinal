package com.example.pikachufinal.Pikachuapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class TodosPoke (@PrimaryKey val pokemon: String,
val name: String,
val image:String,
                      val abilities: String,
                      val types: String,
                      val rank: Double=0.0
)
