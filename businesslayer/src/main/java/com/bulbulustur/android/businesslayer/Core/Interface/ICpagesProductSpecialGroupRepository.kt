package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CpagesProductSpecialGroupDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CpagesProductSpecialGroupInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CpagesProductSpecialGroupUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICpagesProductSpecialGroupRepository {

    @GET("api/CpagesProductSpecialGroup/GetCpagesProductSpecialGroupListAsync")
    suspend fun GetCpagesProductSpecialGroupListAsync():
            Result<List<CpagesProductSpecialGroupDTO>>

    @GET("api/CpagesProductSpecialGroup/GetCpagesProductSpecialGroupByIdAsync")
    suspend fun GetCpagesProductSpecialGroupByIdAsync(
        @Query("cpagesProductSpecialGroupId")
        cpagesProductSpecialGroupId: Int
    ): Result<CpagesProductSpecialGroupUpdateModel?>

    @GET("api/CpagesProductSpecialGroup/GetCpagesProductSpecialGroupByIdExtendedAsync")
    suspend fun GetCpagesProductSpecialGroupByIdExtendedAsync(
        @Query("cpagesProductSpecialGroupId")
        cpagesProductSpecialGroupId: Int
    ): Result<CpagesProductSpecialGroupDTO?>

    @POST("api/CpagesProductSpecialGroup/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CpagesProductSpecialGroupInsertModel
    ): Result<Unit>

    @POST("api/CpagesProductSpecialGroup/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CpagesProductSpecialGroupUpdateModel
    ): Result<Unit>

    @POST("api/CpagesProductSpecialGroup/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("cpagesProductSpecialGroupId")
        cpagesProductSpecialGroupId: Int
    ): Result<Unit>
}
