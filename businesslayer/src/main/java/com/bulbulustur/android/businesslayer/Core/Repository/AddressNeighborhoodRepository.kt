package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AddressNeighborhoodDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressNeighborhoodRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressNeighborhoodInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressNeighborhoodUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AddressNeighborhoodRepository(
    private val apiClient: ApiClient = ApiClient
) : IAddressNeighborhoodRepository {

    override suspend fun GetAddressNeighborhoodListAsync(): Result<List<AddressNeighborhoodDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressNeighborhoodListAsync"
        )
    }

    override suspend fun GetAddressNeighborhoodByIdAsync(
        addressNeighborhoodId: Int
    ): Result<AddressNeighborhoodUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressNeighborhoodByIdAsync",
            query = "addressNeighborhoodId=$addressNeighborhoodId"
        )
    }

    override suspend fun GetAddressNeighborhoodByIdExtendedAsync(
        addressNeighborhoodId: Int
    ): Result<AddressNeighborhoodDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAddressNeighborhoodByIdExtendedAsync",
            query = "addressNeighborhoodId=$addressNeighborhoodId"
        )
    }

    override suspend fun InsertAsync(
        model: AddressNeighborhoodInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: AddressNeighborhoodUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        addressNeighborhoodId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "addressNeighborhoodId=$addressNeighborhoodId"
        )
    }
}