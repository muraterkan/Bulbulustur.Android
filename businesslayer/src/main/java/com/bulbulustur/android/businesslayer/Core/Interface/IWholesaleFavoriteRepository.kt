package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleFavoriteUpdateModel

interface IWholesaleFavoriteRepository {

    suspend fun GetWholesaleFavoriteListAsync(): Result<List<WholesaleFavoriteDTO>>

    suspend fun GetWholesaleFavoriteByIdAsync(
        wholesaleFavoriteId: Int
    ): Result<WholesaleFavoriteUpdateModel?>

    suspend fun GetWholesaleFavoriteByIdExtendedAsync(
        wholesaleFavoriteId: Int
    ): Result<WholesaleFavoriteDTO?>
}
