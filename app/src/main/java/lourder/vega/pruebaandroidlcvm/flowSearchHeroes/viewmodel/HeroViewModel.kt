package lourder.vega.pruebaandroidlcvm.flowSearchHeroes.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import lourder.vega.pruebaandroidlcvm.domain.model.response.HeroDetails
import lourder.vega.pruebaandroidlcvm.domain.model.response.HeroList
import lourder.vega.pruebaandroidlcvm.domain.repository.HeroRepository
import lourder.vega.pruebaandroidlcvm.utils.NetworkResult

class HeroViewModel(): ViewModel() {

    private val repository: HeroRepository = HeroRepository()

    var getHeroes = MutableLiveData<NetworkResult<List<HeroList>>>()
    var getHero = MutableLiveData<NetworkResult<HeroDetails>>()

    fun getHeroes() = viewModelScope.launch (Dispatchers.IO){
        repository.getHeroes().collect{
            getHeroes.postValue(it)
        }
    }

    fun getHero(id: Int) = viewModelScope.launch (Dispatchers.IO){
        repository.getHero(id).collect {
            getHero.postValue(it)
        }
    }
}