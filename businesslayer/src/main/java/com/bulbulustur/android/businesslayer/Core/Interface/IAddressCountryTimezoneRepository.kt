package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryTimezoneDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCountryTimezoneInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryTimezoneUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IAddressCountryTimezoneRepository {

    @GET("api/AddressCountryTimezone/GetAddressCountryTimezoneListAsync")
    suspend fun GetAddressCountryTimezoneListAsync():
            Result<List<AddressCountryTimezoneDTO>>

    @GET("api/AddressCountryTimezone/GetAddressCountryTimezoneByIdAsync")
    suspend fun GetAddressCountryTimezoneByIdAsync(
        @Query("addressCountryTimezoneId")
        addressCountryTimezoneId: Int
    ): Result<AddressCountryTimezoneUpdateModel?>

    @GET("api/AddressCountryTimezone/GetAddressCountryTimezoneByIdExtendedAsync")
    suspend fun GetAddressCountryTimezoneByIdExtendedAsync(
        @Query("addressCountryTimezoneId")
        addressCountryTimezoneId: Int
    ): Result<AddressCountryTimezoneDTO?>

    @POST("api/AddressCountryTimezone/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: AddressCountryTimezoneInsertModel
    ): Result<Unit>

    @POST("api/AddressCountryTimezone/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: AddressCountryTimezoneUpdateModel
    ): Result<Unit>

    @POST("api/AddressCountryTimezone/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("addressCountryTimezoneId")
        addressCountryTimezoneId: Int
    ): Result<Unit>
}
