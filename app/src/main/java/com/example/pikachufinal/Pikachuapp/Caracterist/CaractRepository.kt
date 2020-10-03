package com.example.pikachufinal.Pikachuapp.Caracterist

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.pikachufinal.Pikachuapp.retrofit.RetrofitPokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class CaractRepository(private val characteristicDao: CharacteristicDao) {
    private val service = RetrofitPokemon.retrofitInstance()
    val mLiveDataC = characteristicDao.getAllCaractFromBD()

    fun obtainCaractById(url: String): LiveData<Characteristic> {
        return characteristicDao.getCaractByID(url)

    }

    fun obtainDataInternet(name: String) {

        val call = service.fetchCaracter(name).apply {
            enqueue(object : retrofit2.Callback<characteristicApi> {

                override fun onFailure(call: Call<characteristicApi>, t: Throwable) {
                    Log.e("Repository", t.message.toString())
                }

                override fun onResponse(
                    call: Call<characteristicApi>,
                    response: Response<characteristicApi>
                ) {

                    when (response.code()) {
                        in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                            response.body()?.let {
                                characteristicDao.insertAllCharac(converter(it.results))
                                Log.d("nicolsFINAL", it.results.toString())
                            }
                        }
                        in 300..399 -> Log.d("ERROR 300", response.errorBody().toString())
                    }
                }

            })
        }
    }

        fun converter(list: List<Result>): List<Characteristic> {
            var caractpokemon: MutableList<Characteristic> = mutableListOf<Characteristic>()
            list.map {
                caractpokemon.add(Characteristic(it.url))
            }
            return caractpokemon
        }

    }

