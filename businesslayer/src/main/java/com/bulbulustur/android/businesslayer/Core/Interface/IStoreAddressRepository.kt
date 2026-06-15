package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.StoreAddressDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.StoreAddressUpdateModel

interface IStoreAddressRepository {

    suspend fun GetStoreAddressListAsync(): Result<List<StoreAddressDTO>>

    suspend fun GetStoreAddressByIdAsync(
        storeAddressId: Int
    ): Result<StoreAddressUpdateModel?>

    suspend fun GetStoreAddressByIdExtendedAsync(
        storeAddressId: Int
    ): Result<StoreAddressDTO?>
}
