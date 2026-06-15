package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberActivityDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberActivityUpdateModel

interface IMemberActivityRepository {

    suspend fun GetMemberActivityListAsync(): Result<List<MemberActivityDTO>>

    suspend fun GetMemberActivityByIdAsync(
        memberActivityId: Int
    ): Result<MemberActivityUpdateModel?>

    suspend fun GetMemberActivityByIdExtendedAsync(
        memberActivityId: Int
    ): Result<MemberActivityDTO?>
}
