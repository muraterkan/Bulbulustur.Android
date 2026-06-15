package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.StoreDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IStoreRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.StoreUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class StoreRepository(
    private val apiClient: ApiClient
) : IStoreRepository {

    override suspend fun GetStoreListAsync(): Result<List<StoreDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetStoreByIdAsync(
        storeId: Int
    ): Result<StoreUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetStoreByIdExtendedAsync(
        storeId: Int
    ): Result<StoreDTO?> {
        TODO("Not implemented yet")
    }
}
