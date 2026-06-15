package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentGroupLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductCategoryContentGroupLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCategoryContentGroupLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleProductCategoryContentGroupLanguageRepository {

    @GET("api/WholesaleProductCategoryContentGroupLanguage/GetWholesaleProductCategoryContentGroupLanguageListAsync")
    suspend fun GetWholesaleProductCategoryContentGroupLanguageListAsync():
            Result<List<WholesaleProductCategoryContentGroupLanguageDTO>>

    @GET("api/WholesaleProductCategoryContentGroupLanguage/GetWholesaleProductCategoryContentGroupLanguageByIdAsync")
    suspend fun GetWholesaleProductCategoryContentGroupLanguageByIdAsync(
        @Query("wholesaleProductCategoryContentGroupLanguageId")
        wholesaleProductCategoryContentGroupLanguageId: Int
    ): Result<WholesaleProductCategoryContentGroupLanguageUpdateModel?>

    @GET("api/WholesaleProductCategoryContentGroupLanguage/GetWholesaleProductCategoryContentGroupLanguageByIdExtendedAsync")
    suspend fun GetWholesaleProductCategoryContentGroupLanguageByIdExtendedAsync(
        @Query("wholesaleProductCategoryContentGroupLanguageId")
        wholesaleProductCategoryContentGroupLanguageId: Int
    ): Result<WholesaleProductCategoryContentGroupLanguageDTO?>

    @POST("api/WholesaleProductCategoryContentGroupLanguage/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleProductCategoryContentGroupLanguageInsertModel
    ): Result<Unit>

    @POST("api/WholesaleProductCategoryContentGroupLanguage/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleProductCategoryContentGroupLanguageUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleProductCategoryContentGroupLanguage/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleProductCategoryContentGroupLanguageId")
        wholesaleProductCategoryContentGroupLanguageId: Int
    ): Result<Unit>
}
