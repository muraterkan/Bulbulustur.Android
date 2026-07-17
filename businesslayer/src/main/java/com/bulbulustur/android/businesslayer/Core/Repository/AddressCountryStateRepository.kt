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

    override suspend fun GetAddressCountryStatesAsync(countryId: Int, count: Int): Result<List<AddressCountryStateDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "AddressCountryState/GetAddressCountryStatesAsync",
            query = "countryId=$countryId&count=$count"
        )
    }

    override suspend fun GetAddressCountryStateByIdAsync(addressCountryStateId: Int): Result<AddressCountryStateUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "AddressCountryState/GetAddressCountryStateByIdAsync",
            query = "addressCountryStateId=$addressCountryStateId"
        )
    }

    override suspend fun GetAddressCountryStateByIdExtendedAsync(addressCountryStateId: Int): Result<AddressCountryStateDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "AddressCountryState/GetAddressCountryStateByIdExtendedAsync",
            query = "addressCountryStateId=$addressCountryStateId"
        )
    }

    override suspend fun InsertAsync(model: AddressCountryStateInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "AddressCountryState/AddressCountryStateInsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: AddressCountryStateUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "AddressCountryState/AddressCountryStateUpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(addressCountryStateId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_BASE_URL,
            method = "AddressCountryState/AddressCountryStateDelete",
            query = "addressCountryStateId=$addressCountryStateId"
        )
    }
}