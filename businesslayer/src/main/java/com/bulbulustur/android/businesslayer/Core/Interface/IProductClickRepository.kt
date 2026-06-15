package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductClickDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductClickUpdateModel

interface IProductClickRepository {

    suspend fun GetProductClickListAsync(): Result<List<ProductClickDTO>>

    suspend fun GetProductClickByIdAsync(
        productClickId: Int
    ): Result<ProductClickUpdateModel?>

    suspend fun GetProductClickByIdExtendedAsync(
        productClickId: Int
    ): Result<ProductClickDTO?>
}
