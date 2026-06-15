package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.BannerDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BannerInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BannerUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IBannerRepository {

    @GET("api/Banner/GetBannerListAsync")
    suspend fun GetBannerListAsync():
            Result<List<BannerDTO>>

    @GET("api/Banner/GetBannerByIdAsync")
    suspend fun GetBannerByIdAsync(
        @Query("bannerId")
        bannerId: Int
    ): Result<BannerUpdateModel?>

    @GET("api/Banner/GetBannerByIdExtendedAsync")
    suspend fun GetBannerByIdExtendedAsync(
        @Query("bannerId")
        bannerId: Int
    ): Result<BannerDTO?>

    @POST("api/Banner/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: BannerInsertModel
    ): Result<Unit>

    @POST("api/Banner/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: BannerUpdateModel
    ): Result<Unit>

    @POST("api/Banner/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("bannerId")
        bannerId: Int
    ): Result<Unit>
}
