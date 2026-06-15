package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryStateDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCountryStateInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryStateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IAddressCountryStateRepository {

    @GET("api/AddressCountryState/GetAddressCountryStateListAsync")
    suspend fun GetAddressCountryStateListAsync():
            Result<List<AddressCountryStateDTO>>

    @GET("api/AddressCountryState/GetAddressCountryStateByIdAsync")
    suspend fun GetAddressCountryStateByIdAsync(
        @Query("addressCountryStateId")
        addressCountryStateId: Int
    ): Result<AddressCountryStateUpdateModel?>

    @GET("api/AddressCountryState/GetAddressCountryStateByIdExtendedAsync")
    suspend fun GetAddressCountryStateByIdExtendedAsync(
        @Query("addressCountryStateId")
        addressCountryStateId: Int
    ): Result<AddressCountryStateDTO?>

    @POST("api/AddressCountryState/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: AddressCountryStateInsertModel
    ): Result<Unit>

    @POST("api/AddressCountryState/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: AddressCountryStateUpdateModel
    ): Result<Unit>

    @POST("api/AddressCountryState/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("addressCountryStateId")
        addressCountryStateId: Int
    ): Result<Unit>
}
