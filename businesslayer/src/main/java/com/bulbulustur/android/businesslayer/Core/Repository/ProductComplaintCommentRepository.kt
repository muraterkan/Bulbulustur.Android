package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductComplaintCommentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductComplaintCommentRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductComplaintCommentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductComplaintCommentRepository(
    private val apiClient: ApiClient
) : IProductComplaintCommentRepository {

    override suspend fun GetProductComplaintCommentListAsync(): Result<List<ProductComplaintCommentDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductComplaintCommentByIdAsync(
        complaintCommentId: Int
    ): Result<ProductComplaintCommentUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductComplaintCommentByIdExtendedAsync(
        complaintCommentId: Int
    ): Result<ProductComplaintCommentDTO?> {
        TODO("Not implemented yet")
    }
}
