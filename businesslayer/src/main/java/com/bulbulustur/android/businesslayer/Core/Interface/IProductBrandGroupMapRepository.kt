package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandGroupMapDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrandGroupMapUpdateModel

interface IProductBrandGroupMapRepository {

    suspend fun GetProductBrandGroupMapListAsync(): Result<List<ProductBrandGroupMapDTO>>

    suspend fun GetProductBrandGroupMapByIdAsync(
        brandGroupMapId: Int
    ): Result<ProductBrandGroupMapUpdateModel?>

    suspend fun GetProductBrandGroupMapByIdExtendedAsync(
        brandGroupMapId: Int
    ): Result<ProductBrandGroupMapDTO?>
}
