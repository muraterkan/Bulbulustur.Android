package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBuyerCustomizeRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerCustomizeRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBuyerCustomizeRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IWholesaleBuyerCustomizeRequestRepository {
    suspend fun GetWholesaleBuyerCustomizeRequestListAsync(wholesaleProductId: Int, count: Int = 100): Result<List<WholesaleBuyerCustomizeRequestDTO>>
    suspend fun GetWholesaleBuyerCustomizeRequestByIdAsync(wholesaleBuyerCustomizeRequestId: Int): Result<WholesaleBuyerCustomizeRequestUpdateModel?>
    suspend fun GetWholesaleBuyerCustomizeRequestByIdExtendedAsync(languageId: Int, wholesaleBuyerCustomizeRequestId: Int): Result<WholesaleBuyerCustomizeRequestDTO?>
    suspend fun InsertAsync(languageId: Int, model: WholesaleBuyerCustomizeRequestInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: WholesaleBuyerCustomizeRequestUpdateModel): Result<Unit>
    suspend fun DeleteAsync(wholesaleBuyerCustomizeRequestId: Int): Result<Unit>
}
