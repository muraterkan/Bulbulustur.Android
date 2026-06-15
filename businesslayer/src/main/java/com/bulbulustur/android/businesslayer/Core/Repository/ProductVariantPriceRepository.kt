package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPriceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductVariantPriceRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantPriceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductVariantPriceRepository(
    private val apiClient: ApiClient
) : IProductVariantPriceRepository {

    override suspend fun GetProductVariantPriceListAsync(): Result<List<ProductVariantPriceDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductVariantPriceByIdAsync(
        productVariantPriceId: Int
    ): Result<ProductVariantPriceUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductVariantPriceByIdExtendedAsync(
        productVariantPriceId: Int
    ): Result<ProductVariantPriceDTO?> {
        TODO("Not implemented yet")
    }
}
