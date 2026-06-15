package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleFavoriteRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleFavoriteUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleFavoriteRepository(
    private val apiClient: ApiClient
) : IWholesaleFavoriteRepository {

    override suspend fun GetWholesaleFavoriteListAsync(): Result<List<WholesaleFavoriteDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleFavoriteByIdAsync(
        wholesaleFavoriteId: Int
    ): Result<WholesaleFavoriteUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleFavoriteByIdExtendedAsync(
        wholesaleFavoriteId: Int
    ): Result<WholesaleFavoriteDTO?> {
        TODO("Not implemented yet")
    }
}
