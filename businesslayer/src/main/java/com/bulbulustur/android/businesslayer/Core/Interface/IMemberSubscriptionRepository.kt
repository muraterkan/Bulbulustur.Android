package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberSubscriptionDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberSubscriptionRepository {

    suspend fun GetAccountSubscriptionsAsync(memberId: Int, count: Int = 100): Result<List<MemberSubscriptionDTO>>

    suspend fun GetAccountSubscriptionByIdExtendedAsync(memberId: Int, memberSubscriptionId: Int): Result<MemberSubscriptionDTO?>
}