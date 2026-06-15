package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.AddressNeighborhoodDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAddressNeighborhoodRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressNeighborhoodUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class AddressNeighborhoodRepository(
    private val apiClient: ApiClient
) : IAddressNeighborhoodRepository {

    override suspend fun GetAddressNeighborhoodListAsync(): Result<List<AddressNeighborhoodDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAddressNeighborhoodByIdAsync(
        addressNeighborhoodId: Int
    ): Result<AddressNeighborhoodUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetAddressNeighborhoodByIdExtendedAsync(
        addressNeighborhoodId: Int
    ): Result<AddressNeighborhoodDTO?> {
        TODO("Not implemented yet")
    }
}
