package lourder.vega.pruebaandroidlcvm.domain

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    object RetrofitBuilder {

        private const val BASE_URL = "https://akabab.github.io/superhero-api/api/"

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiService: ApiService = getRetrofit().create(ApiService::class.java)
    }


}