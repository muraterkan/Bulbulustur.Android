package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AddressNeighborhoodDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressNeighborhoodUpdateModel

interface IAddressNeighborhoodRepository {

    suspend fun GetAddressNeighborhoodListAsync(): Result<List<AddressNeighborhoodDTO>>

    suspend fun GetAddressNeighborhoodByIdAsync(
        addressNeighborhoodId: Int
    ): Result<AddressNeighborhoodUpdateModel?>

    suspend fun GetAddressNeighborhoodByIdExtendedAsync(
        addressNeighborhoodId: Int
    ): Result<AddressNeighborhoodDTO?>
}
