package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductLowPriceReportDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductLowPriceReportRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductLowPriceReportUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductLowPriceReportRepository(
    private val apiClient: ApiClient
) : IProductLowPriceReportRepository {

    override suspend fun GetProductLowPriceReportListAsync(): Result<List<ProductLowPriceReportDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductLowPriceReportByIdAsync(
        productLowPriceReportId: Int
    ): Result<ProductLowPriceReportUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductLowPriceReportByIdExtendedAsync(
        productLowPriceReportId: Int
    ): Result<ProductLowPriceReportDTO?> {
        TODO("Not implemented yet")
    }
}
