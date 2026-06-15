package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberAddressDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAddressUpdateModel

interface IMemberAddressRepository {

    suspend fun GetMemberAddressListAsync(): Result<List<MemberAddressDTO>>

    suspend fun GetMemberAddressByIdAsync(
        memberAddressId: Int
    ): Result<MemberAddressUpdateModel?>

    suspend fun GetMemberAddressByIdExtendedAsync(
        memberAddressId: Int
    ): Result<MemberAddressDTO?>
}
