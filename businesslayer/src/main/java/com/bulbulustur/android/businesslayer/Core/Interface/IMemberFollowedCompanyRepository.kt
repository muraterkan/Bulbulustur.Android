package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberFollowedCompanyDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberFollowedCompanyInsertModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberFollowedCompanyRepository {

    suspend fun GetAccountFollowedCompanies(memberId: Int, count: Int): Result<List<MemberFollowedCompanyDTO>>

    suspend fun InsertAccountFollowedCompany(memberId: Int, model: MemberFollowedCompanyInsertModel): Result<Unit>

    suspend fun DeleteAccountFollowedCompany(memberId: Int, followedCompanyId: Int): Result<Unit>
}