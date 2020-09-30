package com.example.pikachufinal.Pikachuapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class TodosPoke (@PrimaryKey val pokemon: String )
