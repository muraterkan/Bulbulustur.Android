package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TooltipDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TooltipInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TooltipUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ITooltipRepository {

    @GET("api/Tooltip/GetTooltipListAsync")
    suspend fun GetTooltipListAsync():
            Result<List<TooltipDTO>>

    @GET("api/Tooltip/GetTooltipByIdAsync")
    suspend fun GetTooltipByIdAsync(
        @Query("tooltipId")
        tooltipId: Int
    ): Result<TooltipUpdateModel?>

    @GET("api/Tooltip/GetTooltipByIdExtendedAsync")
    suspend fun GetTooltipByIdExtendedAsync(
        @Query("tooltipId")
        tooltipId: Int
    ): Result<TooltipDTO?>

    @POST("api/Tooltip/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: TooltipInsertModel
    ): Result<Unit>

    @POST("api/Tooltip/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: TooltipUpdateModel
    ): Result<Unit>

    @POST("api/Tooltip/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("tooltipId")
        tooltipId: Int
    ): Result<Unit>
}
