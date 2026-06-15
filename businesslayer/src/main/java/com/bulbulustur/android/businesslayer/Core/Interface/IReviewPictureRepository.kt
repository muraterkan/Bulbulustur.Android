package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ReviewPictureDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ReviewPictureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReviewPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IReviewPictureRepository {

    @GET("api/ReviewPicture/GetReviewPictureListAsync")
    suspend fun GetReviewPictureListAsync():
            Result<List<ReviewPictureDTO>>

    @GET("api/ReviewPicture/GetReviewPictureByIdAsync")
    suspend fun GetReviewPictureByIdAsync(
        @Query("reviewPictureId")
        reviewPictureId: Int
    ): Result<ReviewPictureUpdateModel?>

    @GET("api/ReviewPicture/GetReviewPictureByIdExtendedAsync")
    suspend fun GetReviewPictureByIdExtendedAsync(
        @Query("reviewPictureId")
        reviewPictureId: Int
    ): Result<ReviewPictureDTO?>

    @POST("api/ReviewPicture/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ReviewPictureInsertModel
    ): Result<Unit>

    @POST("api/ReviewPicture/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ReviewPictureUpdateModel
    ): Result<Unit>

    @POST("api/ReviewPicture/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("reviewPictureId")
        reviewPictureId: Int
    ): Result<Unit>
}
