package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBuyTogetherDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBuyTogetherRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBuyTogetherUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductBuyTogetherRepository(
    private val apiClient: ApiClient
) : IProductBuyTogetherRepository {

    override suspend fun GetProductBuyTogetherListAsync(): Result<List<ProductBuyTogetherDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductBuyTogetherByIdAsync(
        productBuyTogetherId: Int
    ): Result<ProductBuyTogetherUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductBuyTogetherByIdExtendedAsync(
        productBuyTogetherId: Int
    ): Result<ProductBuyTogetherDTO?> {
        TODO("Not implemented yet")
    }
}
