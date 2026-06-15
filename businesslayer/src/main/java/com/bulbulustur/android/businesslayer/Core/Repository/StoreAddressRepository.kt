package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.StoreAddressDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IStoreAddressRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.StoreAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class StoreAddressRepository(
    private val apiClient: ApiClient
) : IStoreAddressRepository {

    override suspend fun GetStoreAddressListAsync(): Result<List<StoreAddressDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetStoreAddressByIdAsync(
        storeAddressId: Int
    ): Result<StoreAddressUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetStoreAddressByIdExtendedAsync(
        storeAddressId: Int
    ): Result<StoreAddressDTO?> {
        TODO("Not implemented yet")
    }
}
