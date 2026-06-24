package com.bulbulustur.android.Application.Localization

import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage

object LocalizationFallback {

    private val TurkishResources: Map<String, String> = mapOf(
        LocalizationKeys.Common.Back to "Geri",
        LocalizationKeys.Common.Save to "Kaydet",
        LocalizationKeys.Common.Cancel to "İptal",
        LocalizationKeys.Common.Continue to "Devam Et",
        LocalizationKeys.Common.Loading to "Yükleniyor...",
        LocalizationKeys.Common.Error to "Bir hata oluştu",

        LocalizationKeys.Navigation.Home to "Ana Sayfa",
        LocalizationKeys.Navigation.Categories to "Kategoriler",
        LocalizationKeys.Navigation.Basket to "Sepet",
        LocalizationKeys.Navigation.Account to "Hesabım",
        LocalizationKeys.Navigation.More to "Daha Fazla",

        LocalizationKeys.Settings.Title to "Ayarlar",
        LocalizationKeys.Settings.Appearance to "Görünüm",
        LocalizationKeys.Settings.Language to "Uygulama Dili",
        LocalizationKeys.Settings.LanguageDescription to
                "Uygulamada kullanmak istediğiniz dili seçin.",

        LocalizationKeys.Language.Title to "Dil Seçimi",
        LocalizationKeys.Language.HeaderLabel to "Dil",
        LocalizationKeys.Language.ApplicationLanguage to "Uygulama dili",
        LocalizationKeys.Language.HeaderDescription to
                "Bulbulustur uygulamasında kullanılacak dili buradan değiştirebilirsiniz.",
        LocalizationKeys.Language.SelectionTitle to "Dil seçimi",
        LocalizationKeys.Language.SelectionDescription to
                "Uygulama içinde kullanılacak dili seçin.",
        LocalizationKeys.Language.ApplicationSetting to "Uygulama ayarı",

        LocalizationKeys.Language.Turkish to "Türkçe",
        LocalizationKeys.Language.TurkishDescription to
                "Uygulamayı Türkçe kullan.",

        LocalizationKeys.Language.English to "English",
        LocalizationKeys.Language.EnglishDescription to
                "Use the application in English.",

        LocalizationKeys.Language.LocalPreferenceTitle to
                "Yerel dil tercihi",
        LocalizationKeys.Language.LocalPreferenceDescription to
                "Dil tercihiniz cihazda saklanır. Dil kaynakları Bulbulustur Resource API üzerinden yüklenir.",

        LocalizationKeys.BuyerMode.Retail to "Perakende",
        LocalizationKeys.BuyerMode.Wholesale to "Toptan"
    )

    private val EnglishResources: Map<String, String> = mapOf(
        LocalizationKeys.Common.Back to "Back",
        LocalizationKeys.Common.Save to "Save",
        LocalizationKeys.Common.Cancel to "Cancel",
        LocalizationKeys.Common.Continue to "Continue",
        LocalizationKeys.Common.Loading to "Loading...",
        LocalizationKeys.Common.Error to "An error occurred",

        LocalizationKeys.Navigation.Home to "Home",
        LocalizationKeys.Navigation.Categories to "Categories",
        LocalizationKeys.Navigation.Basket to "Basket",
        LocalizationKeys.Navigation.Account to "Account",
        LocalizationKeys.Navigation.More to "More",

        LocalizationKeys.Settings.Title to "Settings",
        LocalizationKeys.Settings.Appearance to "Appearance",
        LocalizationKeys.Settings.Language to "Application Language",
        LocalizationKeys.Settings.LanguageDescription to
                "Select the language you want to use in the application.",

        LocalizationKeys.Language.Title to "Language Selection",
        LocalizationKeys.Language.HeaderLabel to "Language",
        LocalizationKeys.Language.ApplicationLanguage to
                "Application language",
        LocalizationKeys.Language.HeaderDescription to
                "You can change the language used in the Bulbulustur application here.",
        LocalizationKeys.Language.SelectionTitle to "Language selection",
        LocalizationKeys.Language.SelectionDescription to
                "Select the language to use in the application.",
        LocalizationKeys.Language.ApplicationSetting to
                "Application setting",

        LocalizationKeys.Language.Turkish to "Türkçe",
        LocalizationKeys.Language.TurkishDescription to
                "Uygulamayı Türkçe kullan.",

        LocalizationKeys.Language.English to "English",
        LocalizationKeys.Language.EnglishDescription to
                "Use the application in English.",

        LocalizationKeys.Language.LocalPreferenceTitle to
                "Local language preference",
        LocalizationKeys.Language.LocalPreferenceDescription to
                "Your language preference is stored on the device. Language resources are loaded from the Bulbulustur Resource API.",

        LocalizationKeys.BuyerMode.Retail to "Retail",
        LocalizationKeys.BuyerMode.Wholesale to "Wholesale"
    )

    fun Get(
        language: EApplicationLanguage,
        key: String
    ): String? {
        return when (language) {
            EApplicationLanguage.Turkish -> TurkishResources[key]
            EApplicationLanguage.English -> EnglishResources[key]
        }
    }
}