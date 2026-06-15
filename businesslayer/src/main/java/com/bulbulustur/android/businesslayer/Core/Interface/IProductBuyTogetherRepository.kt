package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBuyTogetherDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBuyTogetherUpdateModel

interface IProductBuyTogetherRepository {

    suspend fun GetProductBuyTogetherListAsync(): Result<List<ProductBuyTogetherDTO>>

    suspend fun GetProductBuyTogetherByIdAsync(
        productBuyTogetherId: Int
    ): Result<ProductBuyTogetherUpdateModel?>

    suspend fun GetProductBuyTogetherByIdExtendedAsync(
        productBuyTogetherId: Int
    ): Result<ProductBuyTogetherDTO?>
}
