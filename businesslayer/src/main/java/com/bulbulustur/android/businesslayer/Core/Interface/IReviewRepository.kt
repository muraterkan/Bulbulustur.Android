package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ReviewDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ReviewInsertModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IReviewRepository {

    suspend fun GetReviewsAsync(sourceType: String, sourceId: Int, variantId: Int = 0, page: Int = 1, pageSize: Int = 10): Result<List<ReviewDTO>>

    suspend fun GetMemberReviewsAsync(memberId: Int, count: Int = 100): Result<List<ReviewDTO>>

    suspend fun InsertAsync(insertModel: ReviewInsertModel): Result<Unit>
}