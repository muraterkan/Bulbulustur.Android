package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AddressNeighborhoodDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressNeighborhoodInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressNeighborhoodUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IAddressNeighborhoodRepository {

    @GET("api/AddressNeighborhood/GetAddressNeighborhoodListAsync")
    suspend fun GetAddressNeighborhoodListAsync():
            Result<List<AddressNeighborhoodDTO>>

    @GET("api/AddressNeighborhood/GetAddressNeighborhoodByIdAsync")
    suspend fun GetAddressNeighborhoodByIdAsync(
        @Query("addressNeighborhoodId")
        addressNeighborhoodId: Int
    ): Result<AddressNeighborhoodUpdateModel?>

    @GET("api/AddressNeighborhood/GetAddressNeighborhoodByIdExtendedAsync")
    suspend fun GetAddressNeighborhoodByIdExtendedAsync(
        @Query("addressNeighborhoodId")
        addressNeighborhoodId: Int
    ): Result<AddressNeighborhoodDTO?>

    @POST("api/AddressNeighborhood/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: AddressNeighborhoodInsertModel
    ): Result<Unit>

    @POST("api/AddressNeighborhood/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: AddressNeighborhoodUpdateModel
    ): Result<Unit>

    @POST("api/AddressNeighborhood/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("addressNeighborhoodId")
        addressNeighborhoodId: Int
    ): Result<Unit>
}
