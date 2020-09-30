package com.example.pikachufinal.Pikachuapp.retrofit

import com.example.pikachufinal.Pikachuapp.remoto.PokemonApiPlug
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("pokemon")
    fun fetchPokemon(): Call<PokemonApiPlug>

    @GET("pokemon")
    suspend fun fetchPokemonCorutinas(): Response<PokemonApiPlug>


}