package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CpagesProductSpecialDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CpagesProductSpecialInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CpagesProductSpecialUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICpagesProductSpecialRepository {

    @GET("api/CpagesProductSpecial/GetCpagesProductSpecialListAsync")
    suspend fun GetCpagesProductSpecialListAsync():
            Result<List<CpagesProductSpecialDTO>>

    @GET("api/CpagesProductSpecial/GetCpagesProductSpecialByIdAsync")
    suspend fun GetCpagesProductSpecialByIdAsync(
        @Query("cpagesProductSpecialId")
        cpagesProductSpecialId: Int
    ): Result<CpagesProductSpecialUpdateModel?>

    @GET("api/CpagesProductSpecial/GetCpagesProductSpecialByIdExtendedAsync")
    suspend fun GetCpagesProductSpecialByIdExtendedAsync(
        @Query("cpagesProductSpecialId")
        cpagesProductSpecialId: Int
    ): Result<CpagesProductSpecialDTO?>

    @POST("api/CpagesProductSpecial/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CpagesProductSpecialInsertModel
    ): Result<Unit>

    @POST("api/CpagesProductSpecial/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CpagesProductSpecialUpdateModel
    ): Result<Unit>

    @POST("api/CpagesProductSpecial/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("cpagesProductSpecialId")
        cpagesProductSpecialId: Int
    ): Result<Unit>
}
