package com.example.pikachufinal.Pikachuapp.remoto


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("name")
    val name: String
)