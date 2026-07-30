package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberProfileDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileBioUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileEducationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileJobTitleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberProfileRepository {
    suspend fun GetMemberProfilesAsync(count: Int): Result<List<MemberProfileDTO>>
    suspend fun GetMemberProfileByIdAsync(memberProfileId: Int): Result<MemberProfileUpdateModel?>
    suspend fun GetMemberProfileByIdExtendedAsync(memberProfileId: Int): Result<MemberProfileDTO?>
    suspend fun GetMemberProfileByMemberIdAsync(memberId: Int): Result<MemberProfileDTO?>
    suspend fun UpsertBioAsync(model: MemberProfileBioUpdateModel): Result<MemberProfileDTO?>
    suspend fun UpsertEducationAsync(model: MemberProfileEducationUpdateModel): Result<MemberProfileDTO?>
    suspend fun UpsertJobTitleAsync(model: MemberProfileJobTitleUpdateModel): Result<MemberProfileDTO?>
    suspend fun DeleteAsync(memberProfileId: Int): Result<Unit>
}
