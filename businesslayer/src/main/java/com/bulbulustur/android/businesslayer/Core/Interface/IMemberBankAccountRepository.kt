package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberBankAccountDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberBankAccountInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberBankAccountUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IMemberBankAccountRepository {

    @GET("api/MemberBankAccount/GetMemberBankAccountListAsync")
    suspend fun GetMemberBankAccountListAsync():
            Result<List<MemberBankAccountDTO>>

    @GET("api/MemberBankAccount/GetMemberBankAccountByIdAsync")
    suspend fun GetMemberBankAccountByIdAsync(
        @Query("memberBankAccountId")
        memberBankAccountId: Int
    ): Result<MemberBankAccountUpdateModel?>

    @GET("api/MemberBankAccount/GetMemberBankAccountByIdExtendedAsync")
    suspend fun GetMemberBankAccountByIdExtendedAsync(
        @Query("memberBankAccountId")
        memberBankAccountId: Int
    ): Result<MemberBankAccountDTO?>

    @POST("api/MemberBankAccount/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: MemberBankAccountInsertModel
    ): Result<Unit>

    @POST("api/MemberBankAccount/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: MemberBankAccountUpdateModel
    ): Result<Unit>

    @POST("api/MemberBankAccount/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("memberBankAccountId")
        memberBankAccountId: Int
    ): Result<Unit>
}
