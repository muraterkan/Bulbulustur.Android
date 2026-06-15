package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrowsingHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrowsingHistoryUpdateModel

interface IProductBrowsingHistoryRepository {

    suspend fun GetProductBrowsingHistoryListAsync(): Result<List<ProductBrowsingHistoryDTO>>

    suspend fun GetProductBrowsingHistoryByIdAsync(
        browsingHistoryId: Int
    ): Result<ProductBrowsingHistoryUpdateModel?>

    suspend fun GetProductBrowsingHistoryByIdExtendedAsync(
        browsingHistoryId: Int
    ): Result<ProductBrowsingHistoryDTO?>
}
