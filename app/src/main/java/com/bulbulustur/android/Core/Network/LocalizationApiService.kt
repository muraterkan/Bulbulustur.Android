package com.bulbulustur.android.Core.Network

import com.bulbulustur.android.Core.DTO.ResourceDTO
import com.bulbulustur.android.Core.DTO.ResultDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface LocalizationApiService {

    @GET("GetResourcesAsync")
    suspend fun getResourcesAsync(
        @Query("languageId") languageId: Int,
        @Query("count") count: Int = 10000
    ): ResultDTO<List<ResourceDTO>>
}