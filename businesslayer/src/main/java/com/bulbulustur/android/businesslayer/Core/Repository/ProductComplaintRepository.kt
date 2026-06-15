package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductComplaintDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductComplaintRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductComplaintInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductComplaintUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductComplaintRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductComplaintRepository {

    override suspend fun GetProductComplaintListAsync(): Result<List<ProductComplaintDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductComplaintListAsync"
        )
    }

    override suspend fun GetProductComplaintByIdAsync(
        productComplaintId: Int
    ): Result<ProductComplaintUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductComplaintByIdAsync",
            query = "productComplaintId=$productComplaintId"
        )
    }

    override suspend fun GetProductComplaintByIdExtendedAsync(
        productComplaintId: Int
    ): Result<ProductComplaintDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductComplaintByIdExtendedAsync",
            query = "productComplaintId=$productComplaintId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductComplaintInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductComplaintUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productComplaintId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productComplaintId=$productComplaintId"
        )
    }
}