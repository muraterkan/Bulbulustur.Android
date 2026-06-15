package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberFollowedDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberFollowedUpdateModel

interface IMemberFollowedRepository {

    suspend fun GetMemberFollowedListAsync(): Result<List<MemberFollowedDTO>>

    suspend fun GetMemberFollowedByIdAsync(
        memberFollowedId: Int
    ): Result<MemberFollowedUpdateModel?>

    suspend fun GetMemberFollowedByIdExtendedAsync(
        memberFollowedId: Int
    ): Result<MemberFollowedDTO?>
}
