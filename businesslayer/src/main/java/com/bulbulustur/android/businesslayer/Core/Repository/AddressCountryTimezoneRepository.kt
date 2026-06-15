package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryTimezoneDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCountryTimezoneRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCountryTimezoneInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryTimezoneUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AddressCountryTimezoneRepository(
    private val apiClient: ApiClient = ApiClient
) : IAddressCountryTimezoneRepository {

    override suspend fun GetAddressCountryTimezoneListAsync(): Result<List<AddressCountryTimezoneDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCountryTimezoneListAsync"
        )
    }

    override suspend fun GetAddressCountryTimezoneByIdAsync(
        addressCountryTimezoneId: Int
    ): Result<AddressCountryTimezoneUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCountryTimezoneByIdAsync",
            query = "addressCountryTimezoneId=$addressCountryTimezoneId"
        )
    }

    override suspend fun GetAddressCountryTimezoneByIdExtendedAsync(
        addressCountryTimezoneId: Int
    ): Result<AddressCountryTimezoneDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCountryTimezoneByIdExtendedAsync",
            query = "addressCountryTimezoneId=$addressCountryTimezoneId"
        )
    }

    override suspend fun InsertAsync(
        model: AddressCountryTimezoneInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: AddressCountryTimezoneUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        addressCountryTimezoneId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "addressCountryTimezoneId=$addressCountryTimezoneId"
        )
    }
}