package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryPopularDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryPopularRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryPopularUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductCategoryPopularRepository(
    private val apiClient: ApiClient
) : IProductCategoryPopularRepository {

    override suspend fun GetProductCategoryPopularListAsync(): Result<List<ProductCategoryPopularDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductCategoryPopularByIdAsync(
        productCategoryPopularId: Int
    ): Result<ProductCategoryPopularUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductCategoryPopularByIdExtendedAsync(
        productCategoryPopularId: Int
    ): Result<ProductCategoryPopularDTO?> {
        TODO("Not implemented yet")
    }
}
