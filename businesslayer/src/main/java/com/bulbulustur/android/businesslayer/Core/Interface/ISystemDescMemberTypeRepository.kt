package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMemberTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescMemberTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMemberTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescMemberTypeRepository {

    @GET("api/SystemDescMemberType/GetSystemDescMemberTypeListAsync")
    suspend fun GetSystemDescMemberTypeListAsync():
            Result<List<SystemDescMemberTypeDTO>>

    @GET("api/SystemDescMemberType/GetSystemDescMemberTypeByIdAsync")
    suspend fun GetSystemDescMemberTypeByIdAsync(
        @Query("systemDescMemberTypeId")
        systemDescMemberTypeId: Int
    ): Result<SystemDescMemberTypeUpdateModel?>

    @GET("api/SystemDescMemberType/GetSystemDescMemberTypeByIdExtendedAsync")
    suspend fun GetSystemDescMemberTypeByIdExtendedAsync(
        @Query("systemDescMemberTypeId")
        systemDescMemberTypeId: Int
    ): Result<SystemDescMemberTypeDTO?>

    @POST("api/SystemDescMemberType/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescMemberTypeInsertModel
    ): Result<Unit>

    @POST("api/SystemDescMemberType/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescMemberTypeUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescMemberType/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescMemberTypeId")
        systemDescMemberTypeId: Int
    ): Result<Unit>
}
