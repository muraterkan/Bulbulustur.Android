package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductIncorrectReportDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductIncorrectReportUpdateModel

interface IProductIncorrectReportRepository {

    suspend fun GetProductIncorrectReportListAsync(): Result<List<ProductIncorrectReportDTO>>

    suspend fun GetProductIncorrectReportByIdAsync(
        incorrectReportId: Int
    ): Result<ProductIncorrectReportUpdateModel?>

    suspend fun GetProductIncorrectReportByIdExtendedAsync(
        incorrectReportId: Int
    ): Result<ProductIncorrectReportDTO?>
}
