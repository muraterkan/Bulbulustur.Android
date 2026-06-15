package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberBlockDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberBlockUpdateModel

interface IMemberBlockRepository {

    suspend fun GetMemberBlockListAsync(): Result<List<MemberBlockDTO>>

    suspend fun GetMemberBlockByIdAsync(
        memberBlockId: Int
    ): Result<MemberBlockUpdateModel?>

    suspend fun GetMemberBlockByIdExtendedAsync(
        memberBlockId: Int
    ): Result<MemberBlockDTO?>
}
