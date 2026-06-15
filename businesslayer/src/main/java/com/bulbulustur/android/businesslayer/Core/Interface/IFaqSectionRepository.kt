package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.FaqSectionDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.FaqSectionInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.FaqSectionUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IFaqSectionRepository {

    @GET("api/FaqSection/GetFaqSectionListAsync")
    suspend fun GetFaqSectionListAsync():
            Result<List<FaqSectionDTO>>

    @GET("api/FaqSection/GetFaqSectionByIdAsync")
    suspend fun GetFaqSectionByIdAsync(
        @Query("faqSectionId")
        faqSectionId: Int
    ): Result<FaqSectionUpdateModel?>

    @GET("api/FaqSection/GetFaqSectionByIdExtendedAsync")
    suspend fun GetFaqSectionByIdExtendedAsync(
        @Query("faqSectionId")
        faqSectionId: Int
    ): Result<FaqSectionDTO?>

    @POST("api/FaqSection/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: FaqSectionInsertModel
    ): Result<Unit>

    @POST("api/FaqSection/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: FaqSectionUpdateModel
    ): Result<Unit>

    @POST("api/FaqSection/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("faqSectionId")
        faqSectionId: Int
    ): Result<Unit>
}
