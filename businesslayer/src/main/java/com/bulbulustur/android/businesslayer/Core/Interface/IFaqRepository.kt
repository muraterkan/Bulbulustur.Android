package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.FaqDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.FaqInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.FaqUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IFaqRepository {

    @GET("api/Faq/GetFaqListAsync")
    suspend fun GetFaqListAsync():
            Result<List<FaqDTO>>

    @GET("api/Faq/GetFaqByIdAsync")
    suspend fun GetFaqByIdAsync(
        @Query("faqId")
        faqId: Int
    ): Result<FaqUpdateModel?>

    @GET("api/Faq/GetFaqByIdExtendedAsync")
    suspend fun GetFaqByIdExtendedAsync(
        @Query("faqId")
        faqId: Int
    ): Result<FaqDTO?>

    @POST("api/Faq/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: FaqInsertModel
    ): Result<Unit>

    @POST("api/Faq/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: FaqUpdateModel
    ): Result<Unit>

    @POST("api/Faq/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("faqId")
        faqId: Int
    ): Result<Unit>
}
