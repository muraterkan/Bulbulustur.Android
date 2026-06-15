package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductLowPriceReportDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductLowPriceReportUpdateModel

interface IProductLowPriceReportRepository {

    suspend fun GetProductLowPriceReportListAsync(): Result<List<ProductLowPriceReportDTO>>

    suspend fun GetProductLowPriceReportByIdAsync(
        productLowPriceReportId: Int
    ): Result<ProductLowPriceReportUpdateModel?>

    suspend fun GetProductLowPriceReportByIdExtendedAsync(
        productLowPriceReportId: Int
    ): Result<ProductLowPriceReportDTO?>
}
