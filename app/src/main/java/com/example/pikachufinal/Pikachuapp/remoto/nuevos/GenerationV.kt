package com.example.pikachufinal.Pikachuapp.remoto.nuevos


import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white")
    val blackWhite: BlackWhite
)