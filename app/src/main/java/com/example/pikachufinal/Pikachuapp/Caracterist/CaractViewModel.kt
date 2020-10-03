package com.example.pikachufinal.Pikachuapp.Caracterist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class CaractViewModel(application: Application): AndroidViewModel(application) {
    private var repository: CaractRepository

    init {
        val characteristicDao = CaractDataBase.getDatabase(application).characteristicDao()
        repository = CaractRepository(characteristicDao)

    }

    fun obtainFromInternet(name: String){
        repository.obtainDataInternet(name)
    }


    fun exposeLiveDataFromDatabase(): LiveData<List<Characteristic>> {
        return repository.mLiveDataC
    }

    fun obtainCaractByID(id: String): LiveData<Characteristic>{
        return repository.obtainCaractById(id)
    }


}