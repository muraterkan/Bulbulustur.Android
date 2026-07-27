package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescRoommateRelationshipExpectationDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescRoommateRelationshipExpectationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescRoommateRelationshipExpectationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRoommateRelationshipExpectationRepository {

    suspend fun GetSystemDescRoommateRelationshipExpectationsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescRoommateRelationshipExpectationDTO>>

    suspend fun GetSystemDescRoommateRelationshipExpectationByIdAsync(
        systemDescRoommateRelationshipExpectationId: Int
    ): Result<SystemDescRoommateRelationshipExpectationUpdateModel?>

    suspend fun GetSystemDescRoommateRelationshipExpectationByIdExtendedAsync(
        languageId: Int,
        systemDescRoommateRelationshipExpectationId: Int
    ): Result<SystemDescRoommateRelationshipExpectationDTO?>

    suspend fun InsertAsync(
        model: SystemDescRoommateRelationshipExpectationInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescRoommateRelationshipExpectationUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescRoommateRelationshipExpectationId: Int
    ): Result<Unit>
}