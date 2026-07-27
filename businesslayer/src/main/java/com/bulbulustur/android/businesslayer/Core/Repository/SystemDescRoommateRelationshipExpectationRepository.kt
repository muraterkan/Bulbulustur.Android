package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescRoommateRelationshipExpectationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescRoommateRelationshipExpectationRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescRoommateRelationshipExpectationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescRoommateRelationshipExpectationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRoommateRelationshipExpectationRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRoommateRelationshipExpectationRepository {

    override suspend fun GetSystemDescRoommateRelationshipExpectationsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescRoommateRelationshipExpectationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescRoommateRelationshipExpectationsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescRoommateRelationshipExpectationByIdAsync(
        systemDescRoommateRelationshipExpectationId: Int
    ): Result<SystemDescRoommateRelationshipExpectationUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescRoommateRelationshipExpectationByIdAsync",
            query = "systemDescRoommateRelationshipExpectationId=$systemDescRoommateRelationshipExpectationId"
        )
    }

    override suspend fun GetSystemDescRoommateRelationshipExpectationByIdExtendedAsync(
        languageId: Int,
        systemDescRoommateRelationshipExpectationId: Int
    ): Result<SystemDescRoommateRelationshipExpectationDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescRoommateRelationshipExpectationByIdExtendedAsync",
            query = "languageId=$languageId&systemDescRoommateRelationshipExpectationId=$systemDescRoommateRelationshipExpectationId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescRoommateRelationshipExpectationInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescRoommateRelationshipExpectationAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescRoommateRelationshipExpectationUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescRoommateRelationshipExpectationAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescRoommateRelationshipExpectationId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescRoommateRelationshipExpectationAsync",
            query = "systemDescRoommateRelationshipExpectationId=$systemDescRoommateRelationshipExpectationId"
        )
    }
}