package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberBankAccountDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberBankAccountRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberBankAccountUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class MemberBankAccountRepository(
    private val apiClient: ApiClient
) : IMemberBankAccountRepository {

    override suspend fun GetMemberBankAccountListAsync(): Result<List<MemberBankAccountDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberBankAccountByIdAsync(
        memberBankAccountId: Int
    ): Result<MemberBankAccountUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberBankAccountByIdExtendedAsync(
        memberBankAccountId: Int
    ): Result<MemberBankAccountDTO?> {
        TODO("Not implemented yet")
    }
}
