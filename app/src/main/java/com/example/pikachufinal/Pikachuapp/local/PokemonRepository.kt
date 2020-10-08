package com.example.pikachufinal.Pikachuapp.local

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.pikachufinal.Pikachuapp.dao.PokemonDao
import com.example.pikachufinal.Pikachuapp.entities.TodosPoke
import com.example.pikachufinal.Pikachuapp.remoto.PokemonApiPlug
import com.example.pikachufinal.Pikachuapp.remoto.Result
import com.example.pikachufinal.Pikachuapp.retrofit.Pokemon
import com.example.pikachufinal.Pikachuapp.retrofit.ResponseApi
import com.example.pikachufinal.Pikachuapp.retrofit.RetrofitPokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository(private val pokemonDao: PokemonDao) {
    private val service = RetrofitPokemon.retrofitInstance()
    val mLiveData = pokemonDao.getAllPokemonFromBD()

    fun obtainPokemoninByID(id: String): LiveData<TodosPoke> {
        return pokemonDao.getPokemonByID(id)
    }

    suspend fun updatePokemon(todosPoke: TodosPoke) {
        pokemonDao.updatePokemon(todosPoke)
    }

    fun getDataFromServer() {
        val call = service.fetchPokemon()
        call.enqueue(object : Callback<ResponseApi> {
            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                Log.e("Repository", t.message.toString())
            }

            override fun onResponse(
                call: Call<ResponseApi>,
                response: Response<ResponseApi>
            ) {
                when (response.code()) {
                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let {
                            // pokemonDao.insertAllPokemon(converter(it.results))
                            //Log.d("nicols", it.results.toString())
                            for (item: Pokemon in response.body()!!.results) {

                                pokemonGetAbilities(item.name)
                            }
                        }
                    }
                    in 300..399 -> Log.d("ERROR 300", response.errorBody().toString())
                }
            }
        })
    }

    //fun converter(list: List<Result>): List<TodosPoke> {
      //  var nombrepokemon: MutableList<TodosPoke> = mutableListOf<TodosPoke>()
       // list.map {
         //   nombrepokemon.add(TodosPoke(it.name))
        //}
        //return nombrepokemon
    //}

    fun pokemonGetAbilities(name: String) {
        val call = service.fetchCaracter(name)
        call.enqueue(object : Callback<ResponseApi> {

            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                Log.e("repository", t.message.toString())
            }

            override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                when (response.code()) {
                    in 200..209 -> CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let {
                            Log.d(
                                "NICOLSMOSTRAR",
                                pokemonDao.getPokemonByID(it.id.toString()).toString()
                            )
                            if (pokemonDao.Pokemonactual(it.id.toString()) < 1) {
                                pokemonDao.insertAllPokemon(
                                    TodosPoke(
                                        name = it.name,
                                        pokemon = it.id.toString(),
                                        image = "https://pokeres.bastionbot.org/images/pokemon/" + it.id.toString() + ".png",
                                        abilities = busqueda(it.abilities.map { x -> x.ability.name }
                                            .toString()),
                                        types = busqueda(it.types.map { x -> x.type.name }
                                            .toString())
                                    )
                                )
                            }
                        }

                    }
                    in 300..399 -> Log.d("ERROR 300", response.errorBody().toString())
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
    fun busqueda(data: String): String {
        val mostrar = data.subSequence(1, data.length - 1).toString()
        Log.d("MOSTRAR", mostrar)
        return mostrar
    }
}


