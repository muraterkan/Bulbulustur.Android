package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ContractDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IContractRepository {

    suspend fun GetOrderStoreContractAsync(orderKey: String, storeKey: String): Result<ContractDTO?>
}