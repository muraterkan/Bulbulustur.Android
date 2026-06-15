package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryStateDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCountryStateRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCountryStateInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryStateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AddressCountryStateRepository(
    private val apiClient: ApiClient = ApiClient
) : IAddressCountryStateRepository {

    override suspend fun GetAddressCountryStateListAsync(): Result<List<AddressCountryStateDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCountryStateListAsync"
        )
    }

    override suspend fun GetAddressCountryStateByIdAsync(
        addressCountryStateId: Int
    ): Result<AddressCountryStateUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCountryStateByIdAsync",
            query = "addressCountryStateId=$addressCountryStateId"
        )
    }

    override suspend fun GetAddressCountryStateByIdExtendedAsync(
        addressCountryStateId: Int
    ): Result<AddressCountryStateDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCountryStateByIdExtendedAsync",
            query = "addressCountryStateId=$addressCountryStateId"
        )
    }

    override suspend fun InsertAsync(
        model: AddressCountryStateInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: AddressCountryStateUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        addressCountryStateId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "addressCountryStateId=$addressCountryStateId"
        )
    }
}