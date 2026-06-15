package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrandUpdateModel

interface IProductBrandRepository {

    suspend fun GetProductBrandListAsync(): Result<List<ProductBrandDTO>>

    suspend fun GetProductBrandByIdAsync(
        brandId: Int
    ): Result<ProductBrandUpdateModel?>

    suspend fun GetProductBrandByIdExtendedAsync(
        brandId: Int
    ): Result<ProductBrandDTO?>
}
