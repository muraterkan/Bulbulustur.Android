package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.BannerPictureDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BannerPictureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BannerPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IBannerPictureRepository {

    @GET("api/BannerPicture/GetBannerPictureListAsync")
    suspend fun GetBannerPictureListAsync():
            Result<List<BannerPictureDTO>>

    @GET("api/BannerPicture/GetBannerPictureByIdAsync")
    suspend fun GetBannerPictureByIdAsync(
        @Query("bannerPictureId")
        bannerPictureId: Int
    ): Result<BannerPictureUpdateModel?>

    @GET("api/BannerPicture/GetBannerPictureByIdExtendedAsync")
    suspend fun GetBannerPictureByIdExtendedAsync(
        @Query("bannerPictureId")
        bannerPictureId: Int
    ): Result<BannerPictureDTO?>

    @POST("api/BannerPicture/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: BannerPictureInsertModel
    ): Result<Unit>

    @POST("api/BannerPicture/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: BannerPictureUpdateModel
    ): Result<Unit>

    @POST("api/BannerPicture/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("bannerPictureId")
        bannerPictureId: Int
    ): Result<Unit>
}
