package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductPictureDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductPictureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleProductPictureRepository {

    @GET("api/WholesaleProductPicture/GetWholesaleProductPictureListAsync")
    suspend fun GetWholesaleProductPictureListAsync():
            Result<List<WholesaleProductPictureDTO>>

    @GET("api/WholesaleProductPicture/GetWholesaleProductPictureByIdAsync")
    suspend fun GetWholesaleProductPictureByIdAsync(
        @Query("wholesaleProductPictureId")
        wholesaleProductPictureId: Int
    ): Result<WholesaleProductPictureUpdateModel?>

    @GET("api/WholesaleProductPicture/GetWholesaleProductPictureByIdExtendedAsync")
    suspend fun GetWholesaleProductPictureByIdExtendedAsync(
        @Query("wholesaleProductPictureId")
        wholesaleProductPictureId: Int
    ): Result<WholesaleProductPictureDTO?>

    @POST("api/WholesaleProductPicture/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleProductPictureInsertModel
    ): Result<Unit>

    @POST("api/WholesaleProductPicture/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleProductPictureUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleProductPicture/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleProductPictureId")
        wholesaleProductPictureId: Int
    ): Result<Unit>
}
