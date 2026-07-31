package com.bulbulustur.android.Application.Views.Profile

import com.bulbulustur.android.Application.Localization.BBLocalization

data class ProfileDisplayValues(
    val Education: String = BBLocalization.Current.Get(key = "e23c524e-fedd-4486-ac5e-25721a402156", fallback = "Belirtilmemiş")
)
