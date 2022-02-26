package lourder.vega.pruebaandroidlcvm.domain.repository

import lourder.vega.pruebaandroidlcvm.domain.ApiService
import lourder.vega.pruebaandroidlcvm.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import lourder.vega.pruebaandroidlcvm.domain.RetrofitBuilder
import lourder.vega.pruebaandroidlcvm.domain.model.response.HeroDetails
import lourder.vega.pruebaandroidlcvm.domain.model.response.HeroList
import lourder.vega.pruebaandroidlcvm.utils.BaseApiResponse
import lourder.vega.pruebaandroidlcvm.utils.NetworkResult

class HeroRepository : BaseApiResponse() {

    private val apiService = RetrofitBuilder.RetrofitBuilder.apiService

    suspend fun getHeroes() : Flow<NetworkResult<List<HeroList>>>{
        return flow {
            emit(safeApiCall {
                apiService.getHeroes()
            })
        }
    }

    suspend fun getHero(id: Int) : Flow<NetworkResult<HeroDetails>>{
        return flow{
            emit(safeApiCall {
                apiService.getHero("${id}.json")
            })
        }
    }

}
