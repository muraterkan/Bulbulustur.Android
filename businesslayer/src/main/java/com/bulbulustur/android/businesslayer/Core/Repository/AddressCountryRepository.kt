package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCountryRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCountryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AddressCountryRepository(
    private val apiClient: ApiClient = ApiClient
) : IAddressCountryRepository {

    override suspend fun GetAddressCountryListAsync(): Result<List<AddressCountryDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCountryListAsync"
        )
    }

    override suspend fun GetAddressCountryByIdAsync(
        addressCountryId: Int
    ): Result<AddressCountryUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCountryByIdAsync",
            query = "addressCountryId=$addressCountryId"
        )
    }

    override suspend fun GetAddressCountryByIdExtendedAsync(
        addressCountryId: Int
    ): Result<AddressCountryDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCountryByIdExtendedAsync",
            query = "addressCountryId=$addressCountryId"
        )
    }

    override suspend fun InsertAsync(
        model: AddressCountryInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: AddressCountryUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        addressCountryId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "addressCountryId=$addressCountryId"
        )
    }
}