package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCityDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressCityRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCityInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AddressCityRepository(
    private val apiClient: ApiClient
) : IAddressCityRepository {

    override suspend fun GetAddressCityListAsync(): Result<List<AddressCityDTO>> {
        return apiClient.addressCityApi.GetAddressCityListAsync()
    }

    override suspend fun GetAddressCityByIdAsync(
        addressCityId: Int
    ): Result<AddressCityUpdateModel?> {
        return apiClient.addressCityApi.GetAddressCityByIdAsync(addressCityId)
    }

    override suspend fun GetAddressCityByIdExtendedAsync(
        addressCityId: Int
    ): Result<AddressCityDTO?> {
        return apiClient.addressCityApi.GetAddressCityByIdExtendedAsync(addressCityId)
    }

    override suspend fun InsertAsync(
        model: AddressCityInsertModel
    ): Result<Unit> {
        return apiClient.addressCityApi.InsertAsync(model)
    }

    override suspend fun UpdateAsync(
        model: AddressCityUpdateModel
    ): Result<Unit> {
        return apiClient.addressCityApi.UpdateAsync(model)
    }

    override suspend fun DeleteAsync(
        addressCityId: Int
    ): Result<Unit> {
        return apiClient.addressCityApi.DeleteAsync(addressCityId)
    }
}