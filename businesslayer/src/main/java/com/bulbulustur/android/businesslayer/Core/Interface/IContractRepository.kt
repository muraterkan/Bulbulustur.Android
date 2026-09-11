package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ContractDTO
import com.bulbulustur.android.businesslayer.Core.DTO.CheckoutContractDTO
import com.bulbulustur.android.businesslayer.Core.Model.CheckoutContractRequestModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IContractRepository {

    suspend fun GetOrderStoreContractAsync(orderKey: String, storeKey: String): Result<ContractDTO?>

    suspend fun GetCheckoutContractsAsync(model: CheckoutContractRequestModel): Result<CheckoutContractDTO>
}