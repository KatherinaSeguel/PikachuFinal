package com.example.pikachufinal.Pikachuapp.retrofit

import com.example.pikachufinal.Pikachuapp.Caracterist.characteristicApi
import com.example.pikachufinal.Pikachuapp.remoto.PokemonApiPlug
import com.example.pikachufinal.Pikachuapp.remoto.Result
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("pokemon")
    fun fetchPokemon(): Call<PokemonApiPlug>

    @GET("pokemon")
    suspend fun fetchPokemonCorutinas(): Response<PokemonApiPlug>

@GET("characteristic")
fun fetchCaracter(): Call<characteristicApi>

    @GET("characteristic")
    suspend fun fetchCaractCorutinas(): Response<characteristicApi>

}