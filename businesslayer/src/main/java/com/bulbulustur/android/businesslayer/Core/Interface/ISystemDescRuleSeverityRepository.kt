package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescRuleSeverityDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescRuleSeverityInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescRuleSeverityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRuleSeverityRepository {

    suspend fun GetSystemDescRuleSeveritiesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescRuleSeverityDTO>>

    suspend fun GetSystemDescRuleSeverityByIdAsync(
        systemDescRuleSeverityId: Int
    ): Result<SystemDescRuleSeverityUpdateModel?>

    suspend fun GetSystemDescRuleSeverityByIdExtendedAsync(
        languageId: Int,
        systemDescRuleSeverityId: Int
    ): Result<SystemDescRuleSeverityDTO?>

    suspend fun InsertAsync(
        model: SystemDescRuleSeverityInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescRuleSeverityUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescRuleSeverityId: Int
    ): Result<Unit>
}