package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMaterialTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescMaterialTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMaterialTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescMaterialTypeRepository {

    @GET("api/SystemDescMaterialType/GetSystemDescMaterialTypeListAsync")
    suspend fun GetSystemDescMaterialTypeListAsync():
            Result<List<SystemDescMaterialTypeDTO>>

    @GET("api/SystemDescMaterialType/GetSystemDescMaterialTypeByIdAsync")
    suspend fun GetSystemDescMaterialTypeByIdAsync(
        @Query("systemDescMaterialTypeId")
        systemDescMaterialTypeId: Int
    ): Result<SystemDescMaterialTypeUpdateModel?>

    @GET("api/SystemDescMaterialType/GetSystemDescMaterialTypeByIdExtendedAsync")
    suspend fun GetSystemDescMaterialTypeByIdExtendedAsync(
        @Query("systemDescMaterialTypeId")
        systemDescMaterialTypeId: Int
    ): Result<SystemDescMaterialTypeDTO?>

    @POST("api/SystemDescMaterialType/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescMaterialTypeInsertModel
    ): Result<Unit>

    @POST("api/SystemDescMaterialType/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescMaterialTypeUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescMaterialType/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescMaterialTypeId")
        systemDescMaterialTypeId: Int
    ): Result<Unit>
}
