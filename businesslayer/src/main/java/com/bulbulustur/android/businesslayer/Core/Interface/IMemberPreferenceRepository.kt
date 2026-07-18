package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberPreferenceDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberPreferenceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberPreferenceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberPreferenceRepository {

    suspend fun GetAccountPreferencesAsync(languageId: Int, memberId: Int, count: Int = 100): Result<List<MemberPreferenceDTO>>

    suspend fun InsertAccountPreferenceAsync(memberId: Int, model: MemberPreferenceInsertModel): Result<Unit>

    suspend fun UpdateAccountPreferenceAsync(memberId: Int, model: MemberPreferenceUpdateModel): Result<Unit>
}
