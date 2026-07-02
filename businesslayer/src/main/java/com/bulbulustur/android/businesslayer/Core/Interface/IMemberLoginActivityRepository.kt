package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberLoginActivityDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberLoginActivityRepository {

    suspend fun GetAccountLoginActivities(memberId: Int, count: Int): Result<List<MemberLoginActivityDTO>>
}