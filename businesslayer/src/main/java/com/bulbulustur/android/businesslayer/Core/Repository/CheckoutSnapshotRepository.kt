package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.Interface.ICheckoutSnapshotRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CheckoutSnapshotInsertModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CheckoutSnapshotRepository(
    private val apiClient: ApiClient = ApiClient
) : ICheckoutSnapshotRepository {

    override suspend fun Insert(
        checkoutSnapshot: CheckoutSnapshotInsertModel
    ): Result<Any?> {
        return apiClient.PostAsync<CheckoutSnapshotInsertModel, Any?>(
            baseUrl = ApiRoutes.PAYMENT_BASE_URL,
            method = "CheckoutSnapshot/Insert",
            data = checkoutSnapshot
        )
    }
}
