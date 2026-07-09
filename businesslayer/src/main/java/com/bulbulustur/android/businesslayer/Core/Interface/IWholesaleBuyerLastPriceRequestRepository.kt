package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBuyerLastPriceRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerLastPriceRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBuyerLastPriceRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IWholesaleBuyerLastPriceRequestRepository {
    suspend fun GetWholesaleBuyerLastPriceRequestListAsync(wholesaleProductId: Int, count: Int = 100): Result<List<WholesaleBuyerLastPriceRequestDTO>>
    suspend fun GetWholesaleBuyerLastPriceRequestByIdAsync(wholesaleBuyerLastPriceRequestId: Int): Result<WholesaleBuyerLastPriceRequestUpdateModel?>
    suspend fun GetWholesaleBuyerLastPriceRequestByIdExtendedAsync(languageId: Int, wholesaleBuyerLastPriceRequestId: Int): Result<WholesaleBuyerLastPriceRequestDTO?>
    suspend fun InsertAsync(languageId: Int, model: WholesaleBuyerLastPriceRequestInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: WholesaleBuyerLastPriceRequestUpdateModel): Result<Unit>
    suspend fun DeleteAsync(wholesaleBuyerLastPriceRequestId: Int): Result<Unit>
}
