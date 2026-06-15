package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandGroupDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrandGroupUpdateModel

interface IProductBrandGroupRepository {

    suspend fun GetProductBrandGroupListAsync(): Result<List<ProductBrandGroupDTO>>

    suspend fun GetProductBrandGroupByIdAsync(
        brandGroupId: Int
    ): Result<ProductBrandGroupUpdateModel?>

    suspend fun GetProductBrandGroupByIdExtendedAsync(
        brandGroupId: Int
    ): Result<ProductBrandGroupDTO?>
}
