package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AdvertDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AdvertUpdateModel

interface IAdvertRepository {

    suspend fun GetAdvertListAsync(): Result<List<AdvertDTO>>

    suspend fun GetAdvertByIdAsync(
        advertId: Int
    ): Result<AdvertUpdateModel?>

    suspend fun GetAdvertByIdExtendedAsync(
        advertId: Int
    ): Result<AdvertDTO?>
}
