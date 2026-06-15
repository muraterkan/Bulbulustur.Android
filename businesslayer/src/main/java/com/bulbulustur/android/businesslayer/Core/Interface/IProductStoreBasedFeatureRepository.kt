package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductStoreBasedFeatureDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductStoreBasedFeatureUpdateModel

interface IProductStoreBasedFeatureRepository {

    suspend fun GetProductStoreBasedFeatureListAsync(): Result<List<ProductStoreBasedFeatureDTO>>

    suspend fun GetProductStoreBasedFeatureByIdAsync(
        productStoreBasedFeatureId: Int
    ): Result<ProductStoreBasedFeatureUpdateModel?>

    suspend fun GetProductStoreBasedFeatureByIdExtendedAsync(
        productStoreBasedFeatureId: Int
    ): Result<ProductStoreBasedFeatureDTO?>
}
