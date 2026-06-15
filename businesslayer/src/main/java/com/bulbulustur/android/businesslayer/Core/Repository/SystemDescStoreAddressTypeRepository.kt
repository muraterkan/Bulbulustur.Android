package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescStoreAddressTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescStoreAddressTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescStoreAddressTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescStoreAddressTypeRepository(
    private val apiClient: ApiClient
) : ISystemDescStoreAddressTypeRepository {

    override suspend fun GetSystemDescStoreAddressTypeListAsync(): Result<List<SystemDescStoreAddressTypeDTO>> {
        // TODO: apiClient üzerinden ilgili endpoint çağrısı buraya gelecek
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescStoreAddressTypeByIdAsync(
        systemDescStoreAddressTypeId: Int
    ): Result<SystemDescStoreAddressTypeUpdateModel?> {
        // TODO: apiClient üzerinden ilgili endpoint çağrısı buraya gelecek
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescStoreAddressTypeByIdExtendedAsync(
        systemDescStoreAddressTypeId: Int
    ): Result<SystemDescStoreAddressTypeDTO?> {
        // TODO: apiClient üzerinden ilgili endpoint çağrısı buraya gelecek
        TODO("Not implemented yet")
    }
}