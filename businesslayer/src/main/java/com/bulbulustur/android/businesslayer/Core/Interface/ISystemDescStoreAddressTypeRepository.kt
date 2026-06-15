package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescStoreAddressTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescStoreAddressTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescStoreAddressTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescStoreAddressTypeRepository {

    @GET("api/SystemDescStoreAddressType/GetSystemDescStoreAddressTypeListAsync")
    suspend fun GetSystemDescStoreAddressTypeListAsync():
            Result<List<SystemDescStoreAddressTypeDTO>>

    @GET("api/SystemDescStoreAddressType/GetSystemDescStoreAddressTypeByIdAsync")
    suspend fun GetSystemDescStoreAddressTypeByIdAsync(
        @Query("systemDescStoreAddressTypeId")
        systemDescStoreAddressTypeId: Int
    ): Result<SystemDescStoreAddressTypeUpdateModel?>

    @GET("api/SystemDescStoreAddressType/GetSystemDescStoreAddressTypeByIdExtendedAsync")
    suspend fun GetSystemDescStoreAddressTypeByIdExtendedAsync(
        @Query("systemDescStoreAddressTypeId")
        systemDescStoreAddressTypeId: Int
    ): Result<SystemDescStoreAddressTypeDTO?>

    @POST("api/SystemDescStoreAddressType/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescStoreAddressTypeInsertModel
    ): Result<Unit>

    @POST("api/SystemDescStoreAddressType/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescStoreAddressTypeUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescStoreAddressType/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescStoreAddressTypeId")
        systemDescStoreAddressTypeId: Int
    ): Result<Unit>
}
