package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentGroupDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductCategoryContentGroupInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCategoryContentGroupUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleProductCategoryContentGroupRepository {

    @GET("api/WholesaleProductCategoryContentGroup/GetWholesaleProductCategoryContentGroupListAsync")
    suspend fun GetWholesaleProductCategoryContentGroupListAsync():
            Result<List<WholesaleProductCategoryContentGroupDTO>>

    @GET("api/WholesaleProductCategoryContentGroup/GetWholesaleProductCategoryContentGroupByIdAsync")
    suspend fun GetWholesaleProductCategoryContentGroupByIdAsync(
        @Query("wholesaleProductCategoryContentGroupId")
        wholesaleProductCategoryContentGroupId: Int
    ): Result<WholesaleProductCategoryContentGroupUpdateModel?>

    @GET("api/WholesaleProductCategoryContentGroup/GetWholesaleProductCategoryContentGroupByIdExtendedAsync")
    suspend fun GetWholesaleProductCategoryContentGroupByIdExtendedAsync(
        @Query("wholesaleProductCategoryContentGroupId")
        wholesaleProductCategoryContentGroupId: Int
    ): Result<WholesaleProductCategoryContentGroupDTO?>

    @POST("api/WholesaleProductCategoryContentGroup/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleProductCategoryContentGroupInsertModel
    ): Result<Unit>

    @POST("api/WholesaleProductCategoryContentGroup/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleProductCategoryContentGroupUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleProductCategoryContentGroup/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleProductCategoryContentGroupId")
        wholesaleProductCategoryContentGroupId: Int
    ): Result<Unit>
}
