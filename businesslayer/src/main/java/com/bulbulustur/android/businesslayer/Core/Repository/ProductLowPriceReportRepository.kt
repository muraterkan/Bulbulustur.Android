package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductLowPriceReportDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductLowPriceReportRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductLowPriceReportInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductLowPriceReportUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductLowPriceReportRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductLowPriceReportRepository {

    override suspend fun GetProductLowPriceReportListAsync(): Result<List<ProductLowPriceReportDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_LOW_PRICE_REPORT_BASE_URL,
            method = "GetProductLowPriceReportListAsync"
        )
    }

    override suspend fun GetProductLowPriceReportByIdAsync(
        productLowPriceReportId: Int
    ): Result<ProductLowPriceReportUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_LOW_PRICE_REPORT_BASE_URL,
            method = "GetProductLowPriceReportByIdAsync",
            query = "productLowPriceReportId=$productLowPriceReportId"
        )
    }

    override suspend fun GetProductLowPriceReportByIdExtendedAsync(
        productLowPriceReportId: Int
    ): Result<ProductLowPriceReportDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_LOW_PRICE_REPORT_BASE_URL,
            method = "GetProductLowPriceReportByIdExtendedAsync",
            query = "productLowPriceReportId=$productLowPriceReportId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductLowPriceReportInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_LOW_PRICE_REPORT_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductLowPriceReportUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_LOW_PRICE_REPORT_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productLowPriceReportId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_LOW_PRICE_REPORT_BASE_URL,
            method = "DeleteAsync",
            query = "productLowPriceReportId=$productLowPriceReportId"
        )
    }
}