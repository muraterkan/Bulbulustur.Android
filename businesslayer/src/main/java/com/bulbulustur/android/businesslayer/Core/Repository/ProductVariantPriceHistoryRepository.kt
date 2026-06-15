package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPriceHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductVariantPriceHistoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantPriceHistoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductVariantPriceHistoryRepository(
    private val apiClient: ApiClient
) : IProductVariantPriceHistoryRepository {

    override suspend fun GetProductVariantPriceHistoryListAsync(): Result<List<ProductVariantPriceHistoryDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductVariantPriceHistoryByIdAsync(
        productVariantPriceHistoryId: Int
    ): Result<ProductVariantPriceHistoryUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductVariantPriceHistoryByIdExtendedAsync(
        productVariantPriceHistoryId: Int
    ): Result<ProductVariantPriceHistoryDTO?> {
        TODO("Not implemented yet")
    }
}
