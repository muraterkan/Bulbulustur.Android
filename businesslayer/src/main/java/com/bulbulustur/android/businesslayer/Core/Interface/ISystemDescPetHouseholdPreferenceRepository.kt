package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPetHouseholdPreferenceDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPetHouseholdPreferenceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPetHouseholdPreferenceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescPetHouseholdPreferenceRepository {

    suspend fun GetSystemDescPetHouseholdPreferencesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescPetHouseholdPreferenceDTO>>

    suspend fun GetSystemDescPetHouseholdPreferenceByIdAsync(
        systemDescPetHouseholdPreferenceId: Int
    ): Result<SystemDescPetHouseholdPreferenceUpdateModel?>

    suspend fun GetSystemDescPetHouseholdPreferenceByIdExtendedAsync(
        languageId: Int,
        systemDescPetHouseholdPreferenceId: Int
    ): Result<SystemDescPetHouseholdPreferenceDTO?>

    suspend fun InsertAsync(
        model: SystemDescPetHouseholdPreferenceInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescPetHouseholdPreferenceUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescPetHouseholdPreferenceId: Int
    ): Result<Unit>
}