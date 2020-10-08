package com.example.pikachufinal.Pikachuapp.retrofit

import com.example.pikachufinal.Pikachuapp.Caracterist.characteristicApi
import com.example.pikachufinal.Pikachuapp.remoto.PokemonApiPlug
import com.example.pikachufinal.Pikachuapp.remoto.Result
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("pokemon")
    fun fetchPokemon(): Call<ResponseApi>

@GET("pokemon/{name}")
fun fetchCaracter(@Path("name") name: String): Call<ResponseApi>



}