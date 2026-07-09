package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBuyerSampleRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerSampleRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBuyerSampleRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IWholesaleBuyerSampleRequestRepository {
    suspend fun GetWholesaleBuyerSampleRequestListAsync(wholesaleProductId: Int, count: Int = 100): Result<List<WholesaleBuyerSampleRequestDTO>>
    suspend fun GetWholesaleBuyerSampleRequestByIdExtendedAsync(wholesaleBuyerSampleRequestId: Int): Result<WholesaleBuyerSampleRequestDTO?>
    suspend fun InsertAsync(languageId: Int, model: WholesaleBuyerSampleRequestInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: WholesaleBuyerSampleRequestUpdateModel): Result<Unit>
    suspend fun DeleteAsync(wholesaleBuyerSampleRequestId: Int): Result<Unit>
}
