package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.BuyerRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BuyerRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BuyerRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IBuyerRequestRepository {

    suspend fun GetBuyerRequestsAsync(count: Int = 100): Result<List<BuyerRequestDTO>>

    suspend fun GetBuyerRequestsByMemberAsync(memberId: Int, count: Int = 100): Result<List<BuyerRequestDTO>>

    suspend fun GetBuyerRequestsByIdAsync(buyerRequestKey: String): Result<BuyerRequestUpdateModel?>

    suspend fun GetBuyerRequestsByIdExtendedAsync(buyerRequestKey: String): Result<BuyerRequestDTO?>

    suspend fun InsertAsync(model: BuyerRequestInsertModel): Result<Unit>

    suspend fun UpdateAsync(model: BuyerRequestUpdateModel): Result<Unit>

    suspend fun DeleteAsync(buyerRequestKey: String): Result<Unit>

    suspend fun GetPastRequestsAsync(count: Int = 100): Result<List<BuyerRequestDTO>>
}