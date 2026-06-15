package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ReviewDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ReviewInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReviewUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IReviewRepository {

    @GET("api/Review/GetReviewListAsync")
    suspend fun GetReviewListAsync():
            Result<List<ReviewDTO>>

    @GET("api/Review/GetReviewByIdAsync")
    suspend fun GetReviewByIdAsync(
        @Query("reviewId")
        reviewId: Int
    ): Result<ReviewUpdateModel?>

    @GET("api/Review/GetReviewByIdExtendedAsync")
    suspend fun GetReviewByIdExtendedAsync(
        @Query("reviewId")
        reviewId: Int
    ): Result<ReviewDTO?>

    @POST("api/Review/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ReviewInsertModel
    ): Result<Unit>

    @POST("api/Review/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ReviewUpdateModel
    ): Result<Unit>

    @POST("api/Review/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("reviewId")
        reviewId: Int
    ): Result<Unit>
}
