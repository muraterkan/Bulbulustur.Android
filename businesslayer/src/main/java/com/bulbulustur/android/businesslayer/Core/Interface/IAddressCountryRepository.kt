package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCountryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IAddressCountryRepository {

    @GET("api/AddressCountry/GetAddressCountryListAsync")
    suspend fun GetAddressCountryListAsync():
            Result<List<AddressCountryDTO>>

    @GET("api/AddressCountry/GetAddressCountryByIdAsync")
    suspend fun GetAddressCountryByIdAsync(
        @Query("addressCountryId")
        addressCountryId: Int
    ): Result<AddressCountryUpdateModel?>

    @GET("api/AddressCountry/GetAddressCountryByIdExtendedAsync")
    suspend fun GetAddressCountryByIdExtendedAsync(
        @Query("addressCountryId")
        addressCountryId: Int
    ): Result<AddressCountryDTO?>

    @POST("api/AddressCountry/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: AddressCountryInsertModel
    ): Result<Unit>

    @POST("api/AddressCountry/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: AddressCountryUpdateModel
    ): Result<Unit>

    @POST("api/AddressCountry/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("addressCountryId")
        addressCountryId: Int
    ): Result<Unit>
}
