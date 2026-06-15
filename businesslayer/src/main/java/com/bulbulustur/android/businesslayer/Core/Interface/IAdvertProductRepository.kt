package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AdvertProductDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AdvertProductUpdateModel

interface IAdvertProductRepository {

    suspend fun GetAdvertProductListAsync(): Result<List<AdvertProductDTO>>

    suspend fun GetAdvertProductByIdAsync(
        advertProductId: Int
    ): Result<AdvertProductUpdateModel?>

    suspend fun GetAdvertProductByIdExtendedAsync(
        advertProductId: Int
    ): Result<AdvertProductDTO?>
}
