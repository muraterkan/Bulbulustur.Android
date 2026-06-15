package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductVariantRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductVariantRepository(
    private val apiClient: ApiClient
) : IProductVariantRepository {

    override suspend fun GetProductVariantListAsync(): Result<List<ProductVariantDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductVariantByIdAsync(
        variantId: Int
    ): Result<ProductVariantUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductVariantByIdExtendedAsync(
        variantId: Int
    ): Result<ProductVariantDTO?> {
        TODO("Not implemented yet")
    }
}
