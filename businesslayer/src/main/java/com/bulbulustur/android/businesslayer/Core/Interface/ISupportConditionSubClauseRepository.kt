package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SupportConditionSubClauseDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SupportConditionSubClauseInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SupportConditionSubClauseUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISupportConditionSubClauseRepository {

    @GET("api/SupportConditionSubClause/GetSupportConditionSubClauseListAsync")
    suspend fun GetSupportConditionSubClauseListAsync():
            Result<List<SupportConditionSubClauseDTO>>

    @GET("api/SupportConditionSubClause/GetSupportConditionSubClauseByIdAsync")
    suspend fun GetSupportConditionSubClauseByIdAsync(
        @Query("supportConditionSubClauseId")
        supportConditionSubClauseId: Int
    ): Result<SupportConditionSubClauseUpdateModel?>

    @GET("api/SupportConditionSubClause/GetSupportConditionSubClauseByIdExtendedAsync")
    suspend fun GetSupportConditionSubClauseByIdExtendedAsync(
        @Query("supportConditionSubClauseId")
        supportConditionSubClauseId: Int
    ): Result<SupportConditionSubClauseDTO?>

    @POST("api/SupportConditionSubClause/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SupportConditionSubClauseInsertModel
    ): Result<Unit>

    @POST("api/SupportConditionSubClause/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SupportConditionSubClauseUpdateModel
    ): Result<Unit>

    @POST("api/SupportConditionSubClause/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("supportConditionSubClauseId")
        supportConditionSubClauseId: Int
    ): Result<Unit>
}
