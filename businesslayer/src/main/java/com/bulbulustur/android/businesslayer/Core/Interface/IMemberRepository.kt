package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateModel

interface IMemberRepository {

    suspend fun GetMemberListAsync(): Result<List<MemberDTO>>

    suspend fun GetMemberByIdAsync(
        memberId: Int
    ): Result<MemberUpdateModel?>

    suspend fun GetMemberByIdExtendedAsync(
        memberId: Int
    ): Result<MemberDTO?>
}
