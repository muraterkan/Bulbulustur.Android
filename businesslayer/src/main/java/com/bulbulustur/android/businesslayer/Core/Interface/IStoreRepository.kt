package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.StoreDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.StoreUpdateModel

interface IStoreRepository {

    suspend fun GetStoreListAsync(): Result<List<StoreDTO>>

    suspend fun GetStoreByIdAsync(
        storeId: Int
    ): Result<StoreUpdateModel?>

    suspend fun GetStoreByIdExtendedAsync(
        storeId: Int
    ): Result<StoreDTO?>
}
