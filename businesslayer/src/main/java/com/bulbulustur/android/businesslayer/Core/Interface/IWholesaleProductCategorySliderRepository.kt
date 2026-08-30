package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategorySliderDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IWholesaleProductCategorySliderRepository {
    suspend fun GetWholesaleProductCategorySlider(languageId: Int, productCategoryId: Int): Result<WholesaleProductCategorySliderDTO?>
}
