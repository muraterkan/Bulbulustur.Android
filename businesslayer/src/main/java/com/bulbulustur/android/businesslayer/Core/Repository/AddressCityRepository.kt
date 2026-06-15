package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCityDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCityRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCityInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AddressCityRepository(
    private val apiClient: ApiClient = ApiClient
) : IAddressCityRepository {

    override suspend fun GetAddressCityListAsync(): Result<List<AddressCityDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCityListAsync"
        )
    }

    override suspend fun GetAddressCityByIdAsync(
        addressCityId: Int
    ): Result<AddressCityUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCityByIdAsync",
            query = "addressCityId=$addressCityId"
        )
    }

    override suspend fun GetAddressCityByIdExtendedAsync(
        addressCityId: Int
    ): Result<AddressCityDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressCityByIdExtendedAsync",
            query = "addressCityId=$addressCityId"
        )
    }

    override suspend fun InsertAsync(
        model: AddressCityInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: AddressCityUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        addressCityId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "addressCityId=$addressCityId"
        )
    }
}