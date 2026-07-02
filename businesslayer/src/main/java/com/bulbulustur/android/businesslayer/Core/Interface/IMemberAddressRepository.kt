package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberAddressDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAddressInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberAddressRepository {

    suspend fun GetAccountAddressesAsync(memberId: Int, count: Int): Result<List<MemberAddressDTO>>

    suspend fun GetAccountAddressByIdAsync(memberId: Int, addressKey: String): Result<MemberAddressUpdateModel?>

    suspend fun InsertAccountAddressAsync(memberId: Int, model: MemberAddressInsertModel): Result<Unit>

    suspend fun UpdateAccountAddressAsync(memberId: Int, model: MemberAddressUpdateModel): Result<Unit>

    suspend fun DeleteAccountAddressAsync(memberId: Int, addressId: Int): Result<Unit>
}