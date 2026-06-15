package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SupportConditionDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SupportConditionInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SupportConditionUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISupportConditionRepository {

    @GET("api/SupportCondition/GetSupportConditionListAsync")
    suspend fun GetSupportConditionListAsync():
            Result<List<SupportConditionDTO>>

    @GET("api/SupportCondition/GetSupportConditionByIdAsync")
    suspend fun GetSupportConditionByIdAsync(
        @Query("supportConditionId")
        supportConditionId: Int
    ): Result<SupportConditionUpdateModel?>

    @GET("api/SupportCondition/GetSupportConditionByIdExtendedAsync")
    suspend fun GetSupportConditionByIdExtendedAsync(
        @Query("supportConditionId")
        supportConditionId: Int
    ): Result<SupportConditionDTO?>

    @POST("api/SupportCondition/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SupportConditionInsertModel
    ): Result<Unit>

    @POST("api/SupportCondition/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SupportConditionUpdateModel
    ): Result<Unit>

    @POST("api/SupportCondition/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("supportConditionId")
        supportConditionId: Int
    ): Result<Unit>
}
