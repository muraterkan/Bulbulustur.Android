package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductComplaintCommentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductComplaintCommentRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductComplaintCommentInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductComplaintCommentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductComplaintCommentRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductComplaintCommentRepository {

    override suspend fun GetProductComplaintCommentListAsync(): Result<List<ProductComplaintCommentDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductComplaintCommentListAsync"
        )
    }

    override suspend fun GetProductComplaintCommentByIdAsync(
        productComplaintCommentId: Int
    ): Result<ProductComplaintCommentUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductComplaintCommentByIdAsync",
            query = "productComplaintCommentId=$productComplaintCommentId"
        )
    }

    override suspend fun GetProductComplaintCommentByIdExtendedAsync(
        productComplaintCommentId: Int
    ): Result<ProductComplaintCommentDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductComplaintCommentByIdExtendedAsync",
            query = "productComplaintCommentId=$productComplaintCommentId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductComplaintCommentInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductComplaintCommentUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productComplaintCommentId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productComplaintCommentId=$productComplaintCommentId"
        )
    }
}