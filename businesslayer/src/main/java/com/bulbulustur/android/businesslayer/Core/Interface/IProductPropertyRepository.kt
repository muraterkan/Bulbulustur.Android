package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductPropertyDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductPropertyUpdateModel

interface IProductPropertyRepository {

    suspend fun GetProductPropertyListAsync(): Result<List<ProductPropertyDTO>>

    suspend fun GetProductPropertyByIdAsync(
        propertyId: Int
    ): Result<ProductPropertyUpdateModel?>

    suspend fun GetProductPropertyByIdExtendedAsync(
        propertyId: Int
    ): Result<ProductPropertyDTO?>
}
