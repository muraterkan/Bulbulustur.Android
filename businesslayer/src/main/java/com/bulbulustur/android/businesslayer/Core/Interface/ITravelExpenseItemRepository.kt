package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.TravelExpenseItemDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TravelExpenseItemInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TravelExpenseItemUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ITravelExpenseItemRepository {
    suspend fun GetTravelExpenseItemsAsync(count: Int): Result<List<TravelExpenseItemDTO>>
    suspend fun GetTravelExpenseItemByIdAsync(travelExpenseItemId: Int): Result<TravelExpenseItemUpdateModel?>
    suspend fun GetTravelExpenseItemByIdExtendedAsync(travelExpenseItemId: Int): Result<TravelExpenseItemDTO?>
    suspend fun InsertAsync(model: TravelExpenseItemInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: TravelExpenseItemUpdateModel): Result<Unit>
    suspend fun DeleteAsync(travelExpenseItemId: Int): Result<Unit>
}