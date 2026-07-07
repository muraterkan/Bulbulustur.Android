package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleHomepageFeaturedProductDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IWholesaleHomepageFeaturedProductRepository {
    suspend fun GetHomepageFeaturedProductsAsync(count: Int = 12): Result<List<WholesaleHomepageFeaturedProductDTO>>
}
