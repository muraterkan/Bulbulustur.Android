package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductComplementaryItemDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductComplementaryItemRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductComplementaryItemUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductComplementaryItemRepository(
    private val apiClient: ApiClient
) : IProductComplementaryItemRepository {

    override suspend fun GetProductComplementaryItemListAsync(): Result<List<ProductComplementaryItemDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductComplementaryItemByIdAsync(
        complementaryItemId: Int
    ): Result<ProductComplementaryItemUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductComplementaryItemByIdExtendedAsync(
        complementaryItemId: Int
    ): Result<ProductComplementaryItemDTO?> {
        TODO("Not implemented yet")
    }
}
