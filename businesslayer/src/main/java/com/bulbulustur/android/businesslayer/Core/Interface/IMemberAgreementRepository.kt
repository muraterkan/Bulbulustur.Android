package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberAgreementDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberAgreementRepository {

    suspend fun GetLatestAccountAgreementAsync(memberId: Int): Result<MemberAgreementDTO?>
}