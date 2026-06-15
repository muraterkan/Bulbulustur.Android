package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBrowsingHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleBrowsingHistoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBrowsingHistoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class WholesaleBrowsingHistoryRepository(
    private val apiClient: ApiClient
) : IWholesaleBrowsingHistoryRepository {

    override suspend fun GetWholesaleBrowsingHistoryListAsync(): Result<List<WholesaleBrowsingHistoryDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleBrowsingHistoryByIdAsync(
        wholesaleBrowsingHistoryId: Int
    ): Result<WholesaleBrowsingHistoryUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetWholesaleBrowsingHistoryByIdExtendedAsync(
        wholesaleBrowsingHistoryId: Int
    ): Result<WholesaleBrowsingHistoryDTO?> {
        TODO("Not implemented yet")
    }
}
