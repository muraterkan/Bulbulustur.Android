package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrowsingHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBrowsingHistoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrowsingHistoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductBrowsingHistoryRepository(
    private val apiClient: ApiClient
) : IProductBrowsingHistoryRepository {

    override suspend fun GetProductBrowsingHistoryListAsync(): Result<List<ProductBrowsingHistoryDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductBrowsingHistoryByIdAsync(
        browsingHistoryId: Int
    ): Result<ProductBrowsingHistoryUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductBrowsingHistoryByIdExtendedAsync(
        browsingHistoryId: Int
    ): Result<ProductBrowsingHistoryDTO?> {
        TODO("Not implemented yet")
    }
}
