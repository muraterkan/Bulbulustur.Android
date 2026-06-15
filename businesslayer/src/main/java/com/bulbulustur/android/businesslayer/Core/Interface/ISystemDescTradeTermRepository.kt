package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescTradeTermDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescTradeTermInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescTradeTermUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescTradeTermRepository {

    @GET("api/SystemDescTradeTerm/GetSystemDescTradeTermListAsync")
    suspend fun GetSystemDescTradeTermListAsync():
            Result<List<SystemDescTradeTermDTO>>

    @GET("api/SystemDescTradeTerm/GetSystemDescTradeTermByIdAsync")
    suspend fun GetSystemDescTradeTermByIdAsync(
        @Query("systemDescTradeTermId")
        systemDescTradeTermId: Int
    ): Result<SystemDescTradeTermUpdateModel?>

    @GET("api/SystemDescTradeTerm/GetSystemDescTradeTermByIdExtendedAsync")
    suspend fun GetSystemDescTradeTermByIdExtendedAsync(
        @Query("systemDescTradeTermId")
        systemDescTradeTermId: Int
    ): Result<SystemDescTradeTermDTO?>

    @POST("api/SystemDescTradeTerm/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescTradeTermInsertModel
    ): Result<Unit>

    @POST("api/SystemDescTradeTerm/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescTradeTermUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescTradeTerm/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescTradeTermId")
        systemDescTradeTermId: Int
    ): Result<Unit>
}
