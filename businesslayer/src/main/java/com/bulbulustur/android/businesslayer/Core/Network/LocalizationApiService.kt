/*package com.bulbulustur.android.businesslayer.Core.Network

import com.bulbulustur.android.businesslayer.Core.DTO.ResourceDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ResultDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface LocalizationApiService {

    @GET("GetResourcesAsync")
    suspend fun getResourcesAsync(
        @Query("languageId") languageId: Int,
        @Query("count") count: Int = 10000
    ): ResultDTO<List<ResourceDTO>>
}*/