package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ResourceDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ResourceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ResourceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IResourceRepository {

    @GET("api/Resource/GetResourceListAsync")
    suspend fun GetResourceListAsync():
            Result<List<ResourceDTO>>

    @GET("api/Resource/GetResourceByIdAsync")
    suspend fun GetResourceByIdAsync(
        @Query("resourceId")
        resourceId: Int
    ): Result<ResourceUpdateModel?>

    @GET("api/Resource/GetResourceByIdExtendedAsync")
    suspend fun GetResourceByIdExtendedAsync(
        @Query("resourceId")
        resourceId: Int
    ): Result<ResourceDTO?>

    @POST("api/Resource/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ResourceInsertModel
    ): Result<Unit>

    @POST("api/Resource/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ResourceUpdateModel
    ): Result<Unit>

    @POST("api/Resource/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("resourceId")
        resourceId: Int
    ): Result<Unit>
}
