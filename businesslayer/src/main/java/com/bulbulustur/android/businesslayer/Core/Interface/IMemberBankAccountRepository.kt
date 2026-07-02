package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberBankAccountDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberBankAccountInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberBankAccountUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberBankAccountRepository {

    suspend fun GetAccountBankAccountsAsync(memberId: Int, count: Int): Result<List<MemberBankAccountDTO>>

    suspend fun GetAccountBankAccountByIdAsync(memberId: Int, bankAccountId: Int): Result<MemberBankAccountUpdateModel?>

    suspend fun InsertAccountBankAccountAsync(memberId: Int, model: MemberBankAccountInsertModel): Result<Unit>

    suspend fun UpdateAccountBankAccountAsync(memberId: Int, model: MemberBankAccountUpdateModel): Result<Unit>

    suspend fun DeleteAccountBankAccountAsync(memberId: Int, bankAccountId: Int): Result<Unit>
}