package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescChildrenHouseholdPreferenceDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescChildrenHouseholdPreferenceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescChildrenHouseholdPreferenceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescChildrenHouseholdPreferenceRepository {

    suspend fun GetSystemDescChildrenHouseholdPreferencesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescChildrenHouseholdPreferenceDTO>>

    suspend fun GetSystemDescChildrenHouseholdPreferenceByIdAsync(
        systemDescChildrenHouseholdPreferenceId: Int
    ): Result<SystemDescChildrenHouseholdPreferenceUpdateModel?>

    suspend fun GetSystemDescChildrenHouseholdPreferenceByIdExtendedAsync(
        languageId: Int,
        systemDescChildrenHouseholdPreferenceId: Int
    ): Result<SystemDescChildrenHouseholdPreferenceDTO?>

    suspend fun InsertAsync(
        model: SystemDescChildrenHouseholdPreferenceInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescChildrenHouseholdPreferenceUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescChildrenHouseholdPreferenceId: Int
    ): Result<Unit>
}