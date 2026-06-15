package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductIncorrectReportDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductIncorrectReportRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductIncorrectReportInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductIncorrectReportUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductIncorrectReportRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductIncorrectReportRepository {

    override suspend fun GetProductIncorrectReportListAsync(): Result<List<ProductIncorrectReportDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductIncorrectReportListAsync"
        )
    }

    override suspend fun GetProductIncorrectReportByIdAsync(
        productIncorrectReportId: Int
    ): Result<ProductIncorrectReportUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductIncorrectReportByIdAsync",
            query = "productIncorrectReportId=$productIncorrectReportId"
        )
    }

    override suspend fun GetProductIncorrectReportByIdExtendedAsync(
        productIncorrectReportId: Int
    ): Result<ProductIncorrectReportDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductIncorrectReportByIdExtendedAsync",
            query = "productIncorrectReportId=$productIncorrectReportId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductIncorrectReportInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductIncorrectReportUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productIncorrectReportId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productIncorrectReportId=$productIncorrectReportId"
        )
    }
}