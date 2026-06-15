package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.PaymentAttemptDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PaymentAttemptUpdateModel

interface IPaymentAttemptRepository {

    suspend fun GetPaymentAttemptListAsync(): Result<List<PaymentAttemptDTO>>

    suspend fun GetPaymentAttemptByIdAsync(
        paymentAttemptId: Int
    ): Result<PaymentAttemptUpdateModel?>

    suspend fun GetPaymentAttemptByIdExtendedAsync(
        paymentAttemptId: Int
    ): Result<PaymentAttemptDTO?>
}
