package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductAboutDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductAboutUpdateModel

interface IProductAboutRepository {

    suspend fun GetProductAboutListAsync(): Result<List<ProductAboutDTO>>

    suspend fun GetProductAboutByIdAsync(
        productAboutId: Int
    ): Result<ProductAboutUpdateModel?>

    suspend fun GetProductAboutByIdExtendedAsync(
        productAboutId: Int
    ): Result<ProductAboutDTO?>
}
