package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductStoreBasedFeatureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductStoreBasedFeatureRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductStoreBasedFeatureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductStoreBasedFeatureRepository(
    private val apiClient: ApiClient
) : IProductStoreBasedFeatureRepository {

    override suspend fun GetProductStoreBasedFeatureListAsync(): Result<List<ProductStoreBasedFeatureDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductStoreBasedFeatureByIdAsync(
        productStoreBasedFeatureId: Int
    ): Result<ProductStoreBasedFeatureUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductStoreBasedFeatureByIdExtendedAsync(
        productStoreBasedFeatureId: Int
    ): Result<ProductStoreBasedFeatureDTO?> {
        TODO("Not implemented yet")
    }
}
