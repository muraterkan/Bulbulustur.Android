package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberProfileDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileAlcoholHabitUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileArmpitHairPreferenceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileBioUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileBodyHairPreferenceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileBodyHairUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileBodyTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileBreastSizeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileChildrenPreferenceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileCoupleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileDietTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileEducationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileExerciseHabitUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileHeightUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileJobTitleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileMaritalStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfilePenisSizeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfilePiercingUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfilePubicHairUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileRelationshipTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileReligionUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileSkinToneUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileSmokingHabitUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileTattooUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileWeightUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberProfileRepository {

    suspend fun GetMemberProfilesAsync(count: Int): Result<List<MemberProfileDTO>>

    suspend fun GetMemberProfileByIdAsync(memberProfileId: Int): Result<MemberProfileUpdateModel?>

    suspend fun GetMemberProfileByIdExtendedAsync(memberProfileId: Int): Result<MemberProfileDTO?>

    suspend fun GetMemberProfileByMemberIdAsync(memberId: Int): Result<MemberProfileDTO?>

    suspend fun UpsertBioAsync(model: MemberProfileBioUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertCoupleAsync(model: MemberProfileCoupleUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertHeightAsync(model: MemberProfileHeightUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertWeightAsync(model: MemberProfileWeightUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertBodyTypeAsync(model: MemberProfileBodyTypeUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertSkinToneAsync(model: MemberProfileSkinToneUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertPiercingAsync(model: MemberProfilePiercingUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertTattooAsync(model: MemberProfileTattooUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertReligionAsync(model: MemberProfileReligionUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertDietTypeAsync(model: MemberProfileDietTypeUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertExerciseHabitAsync(model: MemberProfileExerciseHabitUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertAlcoholHabitAsync(model: MemberProfileAlcoholHabitUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertSmokingHabitAsync(model: MemberProfileSmokingHabitUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertMaritalStatusAsync(model: MemberProfileMaritalStatusUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertRelationshipTypeAsync(model: MemberProfileRelationshipTypeUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertChildrenPreferenceAsync(model: MemberProfileChildrenPreferenceUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertEducationAsync(model: MemberProfileEducationUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertJobTitleAsync(model: MemberProfileJobTitleUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertBodyHairAsync(model: MemberProfileBodyHairUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertPubicHairAsync(model: MemberProfilePubicHairUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertArmpitHairPreferenceAsync(model: MemberProfileArmpitHairPreferenceUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertBodyHairPreferenceAsync(model: MemberProfileBodyHairPreferenceUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertPenisSizeAsync(model: MemberProfilePenisSizeUpdateModel): Result<MemberProfileDTO?>

    suspend fun UpsertBreastSizeAsync(model: MemberProfileBreastSizeUpdateModel): Result<MemberProfileDTO?>

    suspend fun DeleteAsync(memberProfileId: Int): Result<Unit>
}