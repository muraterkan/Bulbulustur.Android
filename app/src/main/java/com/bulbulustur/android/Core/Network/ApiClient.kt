package com.bulbulustur.android.Core.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val RESOURCE_BASE_URL = "http://37.60.239.76:30215/api/Resource/"

    val localizationApiService: LocalizationApiService by lazy {
        Retrofit.Builder()
            .baseUrl(RESOURCE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LocalizationApiService::class.java)
    }
}