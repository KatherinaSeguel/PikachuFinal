package com.example.pikachufinal.Pikachuapp.local

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.pikachufinal.Pikachuapp.dao.PokemonDao
import com.example.pikachufinal.Pikachuapp.entities.TodosPoke
import com.example.pikachufinal.Pikachuapp.remoto.PokemonApiPlug
import com.example.pikachufinal.Pikachuapp.retrofit.RetrofitPokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository(private val pokemonDao: PokemonDao ) {
    private val service = RetrofitPokemon.retrofitInstance()
  val mLiveData = pokemonDao.getAllPokemonFromBD()

    fun obtainPokemoninByID(id: String): LiveData<TodosPoke> {
        return pokemonDao.getPokemonByID(id)
    }

fun getDataFromServer() {
    val call = service.fetchPokemon()
    call.enqueue(object :  Callback<PokemonApiPlug> {
        override fun onFailure(call: Call<PokemonApiPlug>, t: Throwable) {
            Log.e("Repository", t.message.toString())
        }

        override fun onResponse(call: Call<PokemonApiPlug>, response: Response<PokemonApiPlug>) {
           when (response.code()) {
               in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                   response.body()?.let {
                      // pokemonDao.insertAllPokemon(it)
                       Log.d("nicols",it.results.toString())
                   }
               }
               in 300..399 -> Log.d("ERROR 300",response.errorBody().toString())
           }
        }

    })
        }

  /*  fun getDataFromServerWithCoroutines() = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { service.fetchPokemonCorutinas() }
        service.onSuccess {
            when(it.code()) {
                in 200..299 -> it.body()?.let { it1 -> pokemonDao.insertAllPokemon(it) }
                in 300..399 -> Log.d("ERROR","ERROR de parametros ETC")
            }
        }
        service.onFailure {
            Log.e("REPO_ERROR",it.message.toString())

        }
    }
*/

}