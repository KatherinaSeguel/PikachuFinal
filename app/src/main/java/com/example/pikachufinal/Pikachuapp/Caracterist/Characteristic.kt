package com.example.pikachufinal.Pikachuapp.Caracterist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "caracteristicas_table")
data class Characteristic(@PrimaryKey val caracter: String) {

}