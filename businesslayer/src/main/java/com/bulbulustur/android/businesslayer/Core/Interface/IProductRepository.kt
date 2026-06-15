package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductUpdateModel

interface IProductRepository {

    suspend fun GetProductListAsync(): Result<List<ProductDTO>>

    suspend fun GetProductByIdAsync(
        productId: Int
    ): Result<ProductUpdateModel?>

    suspend fun GetProductByIdExtendedAsync(
        productId: Int
    ): Result<ProductDTO?>
}
