package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescRoomTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescRoomTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescRoomTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescRoomTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRoomTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRoomTypeRepository {

    override suspend fun GetSystemDescRoomTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescRoomTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescRoomTypesAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescRoomTypeByIdAsync(
        systemDescRoomTypeId: Int
    ): Result<SystemDescRoomTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescRoomTypeByIdAsync",
            query = "systemDescRoomTypeId=$systemDescRoomTypeId"
        )
    }

    override suspend fun GetSystemDescRoomTypeByIdExtendedAsync(
        languageId: Int,
        systemDescRoomTypeId: Int
    ): Result<SystemDescRoomTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetSystemDescRoomTypeByIdExtendedAsync",
            query = "languageId=$languageId&systemDescRoomTypeId=$systemDescRoomTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescRoomTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertSystemDescRoomTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescRoomTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdateSystemDescRoomTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescRoomTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeleteSystemDescRoomTypeAsync",
            query = "systemDescRoomTypeId=$systemDescRoomTypeId"
        )
    }
}