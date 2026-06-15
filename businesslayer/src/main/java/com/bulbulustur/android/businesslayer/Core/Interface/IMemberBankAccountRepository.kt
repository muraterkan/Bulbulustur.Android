package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberBankAccountDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberBankAccountUpdateModel

interface IMemberBankAccountRepository {

    suspend fun GetMemberBankAccountListAsync(): Result<List<MemberBankAccountDTO>>

    suspend fun GetMemberBankAccountByIdAsync(
        memberBankAccountId: Int
    ): Result<MemberBankAccountUpdateModel?>

    suspend fun GetMemberBankAccountByIdExtendedAsync(
        memberBankAccountId: Int
    ): Result<MemberBankAccountDTO?>
}
