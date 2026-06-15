package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ReviewPictureDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReviewPictureUpdateModel

interface IReviewPictureRepository {

    suspend fun GetReviewPictureListAsync(): Result<List<ReviewPictureDTO>>

    suspend fun GetReviewPictureByIdAsync(
        reviewPictureId: Int
    ): Result<ReviewPictureUpdateModel?>

    suspend fun GetReviewPictureByIdExtendedAsync(
        reviewPictureId: Int
    ): Result<ReviewPictureDTO?>
}
