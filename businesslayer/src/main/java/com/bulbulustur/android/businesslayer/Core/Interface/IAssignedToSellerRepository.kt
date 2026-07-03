package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AssignedToSellerDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AssignedToSellerInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AssignedToSellerUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IAssignedToSellerRepository {

    suspend fun GetAssignedToSellersAsync(assignedMemberId: Int): Result<List<AssignedToSellerDTO>>

    suspend fun GetAssignedToSellersByIdAsync(assignedToSellerId: Int): Result<AssignedToSellerUpdateModel?>

    suspend fun GetAssignedToSellersByIdExtendedAsync(assignedToSellerId: Int): Result<AssignedToSellerDTO?>

    suspend fun InsertAsync(model: AssignedToSellerInsertModel): Result<Unit>

    suspend fun UpdateAsync(model: AssignedToSellerUpdateModel): Result<Unit>
}