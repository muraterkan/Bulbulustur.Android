package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberTempDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberTempInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberTempFistdoorModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberTempRepository {

    suspend fun FirstDoorAsync(
        languageId: Int,
        model: MemberTempFistdoorModel
    ): Result<MemberTempInsertModel>

    suspend fun GetMemberTempByActivationCodeAsync(
        languageId: Int,
        uuid: String
    ): Result<MemberTempDTO>
}