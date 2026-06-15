package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageMemberDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageMemberUpdateModel

interface IWholesaleMessageMemberRepository {

    suspend fun GetWholesaleMessageMemberListAsync(): Result<List<WholesaleMessageMemberDTO>>

    suspend fun GetWholesaleMessageMemberByIdAsync(
        wholesaleMessageMemberId: Int
    ): Result<WholesaleMessageMemberUpdateModel?>

    suspend fun GetWholesaleMessageMemberByIdExtendedAsync(
        wholesaleMessageMemberId: Int
    ): Result<WholesaleMessageMemberDTO?>
}
