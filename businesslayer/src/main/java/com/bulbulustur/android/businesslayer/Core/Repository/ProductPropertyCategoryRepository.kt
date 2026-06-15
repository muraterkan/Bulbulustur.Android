package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductPropertyCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductPropertyCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductPropertyCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductPropertyCategoryRepository(
    private val apiClient: ApiClient
) : IProductPropertyCategoryRepository {

    override suspend fun GetProductPropertyCategoryListAsync(): Result<List<ProductPropertyCategoryDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductPropertyCategoryByIdAsync(
        propertyCategoryId: Int
    ): Result<ProductPropertyCategoryUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductPropertyCategoryByIdExtendedAsync(
        propertyCategoryId: Int
    ): Result<ProductPropertyCategoryDTO?> {
        TODO("Not implemented yet")
    }
}
