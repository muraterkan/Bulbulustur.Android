package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.PaymentAttemptDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IPaymentAttemptRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PaymentAttemptUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class PaymentAttemptRepository(
    private val apiClient: ApiClient
) : IPaymentAttemptRepository {

    override suspend fun GetPaymentAttemptListAsync(): Result<List<PaymentAttemptDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetPaymentAttemptByIdAsync(
        paymentAttemptId: Int
    ): Result<PaymentAttemptUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetPaymentAttemptByIdExtendedAsync(
        paymentAttemptId: Int
    ): Result<PaymentAttemptDTO?> {
        TODO("Not implemented yet")
    }
}
