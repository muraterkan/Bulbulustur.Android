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

    override suspend fun GetAddressCountriesAsync(
        languageId: Int,
        count: Int
    ): Result<List<AddressCountryDTO>> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.GLOBALIZATION_BASE_URL,
            method =
                "AddressCountry/GetAddressCountriesAsync",
            query =
                "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetAddressCountryListAsync():
            Result<List<AddressCountryDTO>> {

        return GetAddressCountriesAsync(
            languageId =
                1,
            count =
                300
        )
    }

    override suspend fun GetAddressCountryByIdAsync(
        addressCountryId: Int
    ): Result<AddressCountryUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.GLOBALIZATION_BASE_URL,
            method =
                "AddressCountry/GetAddressCountryByIdAsync",
            query =
                "addressCountryId=$addressCountryId"
        )
    }

    override suspend fun GetAddressCountryByIdExtendedAsync(
        languageId: Int,
        addressCountryId: Int
    ): Result<AddressCountryDTO?> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.GLOBALIZATION_BASE_URL,
            method =
                "AddressCountry/GetAddressCountryByIdExtendedAsync",
            query =
                "languageId=$languageId&addressCountryId=$addressCountryId"
        )
    }

    override suspend fun InsertAsync(
        model: AddressCountryInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl =
                ApiRoutes.GLOBALIZATION_BASE_URL,
            method =
                "AddressCountry/AddressCountryInsertAsync",
            data =
                model
        )
    }

    override suspend fun UpdateAsync(
        model: AddressCountryUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl =
                ApiRoutes.GLOBALIZATION_BASE_URL,
            method =
                "AddressCountry/AddressCountryUpdateAsync",
            data =
                model
        )
    }

    override suspend fun DeleteAsync(
        addressCountryId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl =
                ApiRoutes.GLOBALIZATION_BASE_URL,
            method =
                "AddressCountry/AddressCountryDelete",
            query =
                "addressCountryId=$addressCountryId"
        )
    }
}