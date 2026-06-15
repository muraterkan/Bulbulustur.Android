package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AssignedToSellerDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AssignedToSellerUpdateModel

interface IAssignedToSellerRepository {

    suspend fun GetAssignedToSellerListAsync(): Result<List<AssignedToSellerDTO>>

    suspend fun GetAssignedToSellerByIdAsync(
        assignedToSellerId: Int
    ): Result<AssignedToSellerUpdateModel?>

    suspend fun GetAssignedToSellerByIdExtendedAsync(
        assignedToSellerId: Int
    ): Result<AssignedToSellerDTO?>
}
