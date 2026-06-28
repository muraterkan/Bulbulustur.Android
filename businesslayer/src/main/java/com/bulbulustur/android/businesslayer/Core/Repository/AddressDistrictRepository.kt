package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AddressDistrictDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressDistrictRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressDistrictInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressDistrictUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AddressDistrictRepository(
    private val apiClient: ApiClient = ApiClient
) : IAddressDistrictRepository {

    override suspend fun GetAddressDistrictsAsync(
        countryId: Int,
        countryStateId: Int,
        countryDepartmentId: Int?,
        cityId: Int,
        count: Int
    ): Result<List<AddressDistrictDTO>> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.GLOBALIZATION_BASE_URL,
            method =
                "AddressDistrict/GetAddressDistrictsAsync",
            query =
                buildString {
                    append("countryId=$countryId")
                    append("&countryStateId=$countryStateId")

                    countryDepartmentId?.let {
                        append("&countryDepartmentId=$it")
                    }

                    append("&cityId=$cityId")
                    append("&count=$count")
                }
        )
    }

    override suspend fun GetAddressDistrictListAsync():
            Result<List<AddressDistrictDTO>> {

        return GetAddressDistrictsAsync(
            countryId =
                1,
            countryStateId =
                0,
            countryDepartmentId =
                null,
            cityId =
                0,
            count =
                100
        )
    }

    override suspend fun GetAddressDistrictByIdAsync(
        addressDistrictId: Int
    ): Result<AddressDistrictUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.GLOBALIZATION_BASE_URL,
            method =
                "AddressDistrict/GetAddressDistrictByIdAsync",
            query =
                "addressDistrictId=$addressDistrictId"
        )
    }

    override suspend fun GetAddressDistrictByIdExtendedAsync(
        addressDistrictId: Int
    ): Result<AddressDistrictDTO?> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.GLOBALIZATION_BASE_URL,
            method =
                "AddressDistrict/GetAddressDistrictByIdExtendedAsync",
            query =
                "addressDistrictId=$addressDistrictId"
        )
    }

    override suspend fun InsertAsync(
        model: AddressDistrictInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl =
                ApiRoutes.GLOBALIZATION_BASE_URL,
            method =
                "AddressDistrict/AddressDistrictInsertAsync",
            data =
                model
        )
    }

    override suspend fun UpdateAsync(
        model: AddressDistrictUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl =
                ApiRoutes.GLOBALIZATION_BASE_URL,
            method =
                "AddressDistrict/AddressDistrictUpdateAsync",
            data =
                model
        )
    }

    override suspend fun DeleteAsync(
        addressDistrictId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl =
                ApiRoutes.GLOBALIZATION_BASE_URL,
            method =
                "AddressDistrict/AddressDistrictDelete",
            query =
                "addressDistrictId=$addressDistrictId"
        )
    }
}