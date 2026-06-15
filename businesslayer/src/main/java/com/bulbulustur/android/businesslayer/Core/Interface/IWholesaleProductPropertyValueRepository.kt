package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductPropertyValueDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductPropertyValueInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductPropertyValueUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleProductPropertyValueRepository {

    @GET("api/WholesaleProductPropertyValue/GetWholesaleProductPropertyValueListAsync")
    suspend fun GetWholesaleProductPropertyValueListAsync():
            Result<List<WholesaleProductPropertyValueDTO>>

    @GET("api/WholesaleProductPropertyValue/GetWholesaleProductPropertyValueByIdAsync")
    suspend fun GetWholesaleProductPropertyValueByIdAsync(
        @Query("wholesaleProductPropertyValueId")
        wholesaleProductPropertyValueId: Int
    ): Result<WholesaleProductPropertyValueUpdateModel?>

    @GET("api/WholesaleProductPropertyValue/GetWholesaleProductPropertyValueByIdExtendedAsync")
    suspend fun GetWholesaleProductPropertyValueByIdExtendedAsync(
        @Query("wholesaleProductPropertyValueId")
        wholesaleProductPropertyValueId: Int
    ): Result<WholesaleProductPropertyValueDTO?>

    @POST("api/WholesaleProductPropertyValue/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleProductPropertyValueInsertModel
    ): Result<Unit>

    @POST("api/WholesaleProductPropertyValue/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleProductPropertyValueUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleProductPropertyValue/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleProductPropertyValueId")
        wholesaleProductPropertyValueId: Int
    ): Result<Unit>
}
