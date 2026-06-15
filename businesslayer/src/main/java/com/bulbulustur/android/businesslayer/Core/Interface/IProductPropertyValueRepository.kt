package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductPropertyValueDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductPropertyValueUpdateModel

interface IProductPropertyValueRepository {

    suspend fun GetProductPropertyValueListAsync(): Result<List<ProductPropertyValueDTO>>

    suspend fun GetProductPropertyValueByIdAsync(
        propertyValueId: Int
    ): Result<ProductPropertyValueUpdateModel?>

    suspend fun GetProductPropertyValueByIdExtendedAsync(
        propertyValueId: Int
    ): Result<ProductPropertyValueDTO?>
}
