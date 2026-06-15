package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBrowsingHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBrowsingHistoryUpdateModel

interface IWholesaleBrowsingHistoryRepository {

    suspend fun GetWholesaleBrowsingHistoryListAsync(): Result<List<WholesaleBrowsingHistoryDTO>>

    suspend fun GetWholesaleBrowsingHistoryByIdAsync(
        wholesaleBrowsingHistoryId: Int
    ): Result<WholesaleBrowsingHistoryUpdateModel?>

    suspend fun GetWholesaleBrowsingHistoryByIdExtendedAsync(
        wholesaleBrowsingHistoryId: Int
    ): Result<WholesaleBrowsingHistoryDTO?>
}
