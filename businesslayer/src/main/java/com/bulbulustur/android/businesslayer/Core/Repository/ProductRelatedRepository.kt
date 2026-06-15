package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductRelatedDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductRelatedRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductRelatedUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductRelatedRepository(
    private val apiClient: ApiClient
) : IProductRelatedRepository {

    override suspend fun GetProductRelatedListAsync(): Result<List<ProductRelatedDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductRelatedByIdAsync(
        productRelatedId: Int
    ): Result<ProductRelatedUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductRelatedByIdExtendedAsync(
        productRelatedId: Int
    ): Result<ProductRelatedDTO?> {
        TODO("Not implemented yet")
    }
}
