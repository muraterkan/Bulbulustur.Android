package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CheckoutSnapshotDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CheckoutSnapshotInsertModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ICheckoutSnapshotRepository {
    suspend fun Insert(
        checkoutSnapshot: CheckoutSnapshotInsertModel
    ): Result<Any?>

    suspend fun GetByCheckoutKey(
        checkoutKey: String
    ): Result<CheckoutSnapshotDTO>
}
