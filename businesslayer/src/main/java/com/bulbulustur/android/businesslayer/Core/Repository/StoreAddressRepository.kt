package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.StoreAddressDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IStoreAddressRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.StoreAddressInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.StoreAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class StoreAddressRepository(
    private val apiClient: ApiClient = ApiClient
) : IStoreAddressRepository {

    override suspend fun GetStoreAddressListAsync(): Result<List<StoreAddressDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetStoreAddressListAsync"
        )
    }

    override suspend fun GetStoreAddressByIdAsync(
        storeAddressId: Int
    ): Result<StoreAddressUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetStoreAddressByIdAsync",
            query = "storeAddressId=$storeAddressId"
        )
    }

    override suspend fun GetStoreAddressByIdExtendedAsync(
        storeAddressId: Int
    ): Result<StoreAddressDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetStoreAddressByIdExtendedAsync",
            query = "storeAddressId=$storeAddressId"
        )
    }

    override suspend fun InsertAsync(
        model: StoreAddressInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: StoreAddressUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        storeAddressId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "storeAddressId=$storeAddressId"
        )
    }
}