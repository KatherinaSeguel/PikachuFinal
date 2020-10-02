package com.example.pikachufinal.Pikachuapp.Caracterist


import com.google.gson.annotations.SerializedName

data class characteristicApi(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)