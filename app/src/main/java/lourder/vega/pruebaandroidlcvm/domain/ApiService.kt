package lourder.vega.pruebaandroidlcvm.domain

import lourder.vega.pruebaandroidlcvm.domain.model.response.HeroDetails
import lourder.vega.pruebaandroidlcvm.domain.model.response.HeroList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("all.json")
    suspend fun getHeroes(): Response<List<HeroList>>

    @GET("id/{id}")
    suspend fun getHero(@Path("id") id: String): Response<HeroDetails>
}