package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductFavoriteUpdateModel

interface IProductFavoriteRepository {

    suspend fun GetProductFavoriteListAsync(): Result<List<ProductFavoriteDTO>>

    suspend fun GetProductFavoriteByIdAsync(
        productFavoriteId: Int
    ): Result<ProductFavoriteUpdateModel?>

    suspend fun GetProductFavoriteByIdExtendedAsync(
        productFavoriteId: Int
    ): Result<ProductFavoriteDTO?>
}
