package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberAddressDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAddressInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IMemberAddressRepository {

    @GET("api/MemberAddress/GetMemberAddressListAsync")
    suspend fun GetMemberAddressListAsync():
            Result<List<MemberAddressDTO>>

    @GET("api/MemberAddress/GetMemberAddressByIdAsync")
    suspend fun GetMemberAddressByIdAsync(
        @Query("memberAddressId")
        memberAddressId: Int
    ): Result<MemberAddressUpdateModel?>

    @GET("api/MemberAddress/GetMemberAddressByIdExtendedAsync")
    suspend fun GetMemberAddressByIdExtendedAsync(
        @Query("memberAddressId")
        memberAddressId: Int
    ): Result<MemberAddressDTO?>

    @POST("api/MemberAddress/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: MemberAddressInsertModel
    ): Result<Unit>

    @POST("api/MemberAddress/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: MemberAddressUpdateModel
    ): Result<Unit>

    @POST("api/MemberAddress/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("memberAddressId")
        memberAddressId: Int
    ): Result<Unit>
}
