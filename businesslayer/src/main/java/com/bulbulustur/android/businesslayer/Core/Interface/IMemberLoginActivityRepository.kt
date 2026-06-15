package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberLoginActivityDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberLoginActivityUpdateModel

interface IMemberLoginActivityRepository {

    suspend fun GetMemberLoginActivityListAsync(): Result<List<MemberLoginActivityDTO>>

    suspend fun GetMemberLoginActivityByIdAsync(
        memberLoginActivityId: Int
    ): Result<MemberLoginActivityUpdateModel?>

    suspend fun GetMemberLoginActivityByIdExtendedAsync(
        memberLoginActivityId: Int
    ): Result<MemberLoginActivityDTO?>
}
