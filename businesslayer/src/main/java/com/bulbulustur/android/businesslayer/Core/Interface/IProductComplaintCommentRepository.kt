package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductComplaintCommentDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductComplaintCommentUpdateModel

interface IProductComplaintCommentRepository {

    suspend fun GetProductComplaintCommentListAsync(): Result<List<ProductComplaintCommentDTO>>

    suspend fun GetProductComplaintCommentByIdAsync(
        complaintCommentId: Int
    ): Result<ProductComplaintCommentUpdateModel?>

    suspend fun GetProductComplaintCommentByIdExtendedAsync(
        complaintCommentId: Int
    ): Result<ProductComplaintCommentDTO?>
}
