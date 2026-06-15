package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescStoreAddressTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescStoreAddressTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescStoreAddressTypeRepository {

    suspend fun GetSystemDescStoreAddressTypeListAsync(): Result<List<SystemDescStoreAddressTypeDTO>>

    suspend fun GetSystemDescStoreAddressTypeByIdAsync(
        systemDescStoreAddressTypeId: Int
    ): Result<SystemDescStoreAddressTypeUpdateModel?>

    suspend fun GetSystemDescStoreAddressTypeByIdExtendedAsync(
        systemDescStoreAddressTypeId: Int
    ): Result<SystemDescStoreAddressTypeDTO?>
}