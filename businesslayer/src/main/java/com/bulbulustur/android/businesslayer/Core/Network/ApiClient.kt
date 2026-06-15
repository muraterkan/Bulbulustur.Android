package com.bulbulustur.android.businesslayer.Core.Network

import com.bulbulustur.android.businesslayer.Core.Network.Api.IAddressCityApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "http://37.60.239.76:30215/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val addressCityApi: IAddressCityApi by lazy {
        retrofit.create(IAddressCityApi::class.java)
    }
}
