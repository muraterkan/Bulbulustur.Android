package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductCategoryContentInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCategoryContentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleProductCategoryContentRepository {

    @GET("api/WholesaleProductCategoryContent/GetWholesaleProductCategoryContentListAsync")
    suspend fun GetWholesaleProductCategoryContentListAsync():
            Result<List<WholesaleProductCategoryContentDTO>>

    @GET("api/WholesaleProductCategoryContent/GetWholesaleProductCategoryContentByIdAsync")
    suspend fun GetWholesaleProductCategoryContentByIdAsync(
        @Query("wholesaleProductCategoryContentId")
        wholesaleProductCategoryContentId: Int
    ): Result<WholesaleProductCategoryContentUpdateModel?>

    @GET("api/WholesaleProductCategoryContent/GetWholesaleProductCategoryContentByIdExtendedAsync")
    suspend fun GetWholesaleProductCategoryContentByIdExtendedAsync(
        @Query("wholesaleProductCategoryContentId")
        wholesaleProductCategoryContentId: Int
    ): Result<WholesaleProductCategoryContentDTO?>

    @POST("api/WholesaleProductCategoryContent/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleProductCategoryContentInsertModel
    ): Result<Unit>

    @POST("api/WholesaleProductCategoryContent/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleProductCategoryContentUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleProductCategoryContent/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleProductCategoryContentId")
        wholesaleProductCategoryContentId: Int
    ): Result<Unit>
}
