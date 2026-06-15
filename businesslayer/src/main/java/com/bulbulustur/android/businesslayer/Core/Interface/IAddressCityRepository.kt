package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCityDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCityInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IAddressCityRepository {

    @GET("api/AddressCity/GetAddressCityListAsync")
    suspend fun GetAddressCityListAsync():
            Result<List<AddressCityDTO>>

    @GET("api/AddressCity/GetAddressCityByIdAsync")
    suspend fun GetAddressCityByIdAsync(
        @Query("addressCityId")
        addressCityId: Int
    ): Result<AddressCityUpdateModel?>

    @GET("api/AddressCity/GetAddressCityByIdExtendedAsync")
    suspend fun GetAddressCityByIdExtendedAsync(
        @Query("addressCityId")
        addressCityId: Int
    ): Result<AddressCityDTO?>

    @POST("api/AddressCity/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: AddressCityInsertModel
    ): Result<Unit>

    @POST("api/AddressCity/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: AddressCityUpdateModel
    ): Result<Unit>

    @POST("api/AddressCity/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("addressCityId")
        addressCityId: Int
    ): Result<Unit>
}
