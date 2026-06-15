package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBankDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescBankInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescBankUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescBankRepository {

    @GET("api/SystemDescBank/GetSystemDescBankListAsync")
    suspend fun GetSystemDescBankListAsync():
            Result<List<SystemDescBankDTO>>

    @GET("api/SystemDescBank/GetSystemDescBankByIdAsync")
    suspend fun GetSystemDescBankByIdAsync(
        @Query("systemDescBankId")
        systemDescBankId: Int
    ): Result<SystemDescBankUpdateModel?>

    @GET("api/SystemDescBank/GetSystemDescBankByIdExtendedAsync")
    suspend fun GetSystemDescBankByIdExtendedAsync(
        @Query("systemDescBankId")
        systemDescBankId: Int
    ): Result<SystemDescBankDTO?>

    @POST("api/SystemDescBank/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescBankInsertModel
    ): Result<Unit>

    @POST("api/SystemDescBank/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescBankUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescBank/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescBankId")
        systemDescBankId: Int
    ): Result<Unit>
}
