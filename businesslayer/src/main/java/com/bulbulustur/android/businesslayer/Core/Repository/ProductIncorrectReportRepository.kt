package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductIncorrectReportDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductIncorrectReportRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductIncorrectReportUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductIncorrectReportRepository(
    private val apiClient: ApiClient
) : IProductIncorrectReportRepository {

    override suspend fun GetProductIncorrectReportListAsync(): Result<List<ProductIncorrectReportDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductIncorrectReportByIdAsync(
        incorrectReportId: Int
    ): Result<ProductIncorrectReportUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductIncorrectReportByIdExtendedAsync(
        incorrectReportId: Int
    ): Result<ProductIncorrectReportDTO?> {
        TODO("Not implemented yet")
    }
}
