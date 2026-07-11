package com.bulbulustur.android.Application.Views.Account

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDTO

private const val REGION_SETTINGS_FLAG_ASSET_BASE = "file:///android_asset/flags/"
private const val REGION_SETTINGS_FALLBACK_FLAG = "flag.svg"

@Composable
fun RegionSettingsScreen(
    countries: List<AddressCountryDTO>,
    selectedCountryId: Int,
    isLoading: Boolean,
    errorMessage: String?,
    onCountrySelected: (AddressCountryDTO) -> Unit,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Ülke ve Bölge",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = innerPadding.calculateTopPadding() + BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = innerPadding.calculateBottomPadding() + BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                RegionIntroCard()
            }

            when {
                isLoading && countries.isEmpty() -> {
                    item {
                        RegionLoadingCard()
                    }
                }

                countries.isEmpty() -> {
                    item {
                        RegionMessageCard(
                            message = errorMessage
                                ?.takeIf { it.isNotBlank() }
                                ?: "Kullanılabilir ülke bulunamadı."
                        )
                    }
                }

                else -> {
                    items(
                        items = countries,
                        key = { it.AddressCountryId }
                    ) { country ->
                        RegionRow(
                            item = country,
                            isSelected = country.AddressCountryId == selectedCountryId,
                            onClick = {
                                onCountrySelected(country)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RegionIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Seçiminiz ürün görünürlüğü, teslimat seçenekleri ve yerel içeriklerde kullanılacaktır.",
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun RegionRow(
    item: AddressCountryDTO,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BBRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = RegionFlagAssetPath(item),
                    contentDescription = item.Content,
                    modifier = Modifier.size(BBIcon.SizeLg),
                    contentScale = ContentScale.Fit
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = item.Content,
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = item.IsoShortCode.ifBlank { item.Code },
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = "Seçili ülke",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(BBIcon.SizeLg)
                )
            }
        }
    }
}

@Composable
private fun RegionLoadingCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(BBIcon.SizeLg),
                strokeWidth = BBSpacing.Space1 / 2,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Ülkeler yükleniyor...",
                style = BbTypography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RegionMessageCard(message: String) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = message,
            style = BbTypography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun RegionFlagAssetPath(
    item: AddressCountryDTO
): String {
    val explicitFlag = item.Flag
        .trim()
        .takeIf { it.isNotBlank() }

    if (explicitFlag != null) {
        val normalized = explicitFlag
            .substringAfterLast("/")
            .substringAfterLast("\\")
            .removePrefix("flags/")
            .removePrefix("Flags/")

        if (normalized.endsWith(".svg", ignoreCase = true)) {
            return RegionFlagAssetPath(normalized)
        }
    }

    val iso = item.IsoShortCode
        .ifBlank { item.Code }
        .trim()
        .uppercase()

    val fileName = RegionFlagFileNameByIso(iso)
        ?: RegionFlagFileNameByContent(item.Content)
        ?: REGION_SETTINGS_FALLBACK_FLAG

    return RegionFlagAssetPath(fileName)
}

private fun RegionFlagAssetPath(
    fileName: String
): String {
    return REGION_SETTINGS_FLAG_ASSET_BASE + Uri.encode(fileName)
}

private fun RegionFlagFileNameByContent(
    content: String
): String? {
    val normalized = content
        .trim()
        .lowercase()
        .replace("ı", "i")
        .replace("ğ", "g")
        .replace("ü", "u")
        .replace("ş", "s")
        .replace("ö", "o")
        .replace("ç", "c")

    return when (normalized) {
        "turkiye", "türkiye", "turkey" -> "turkey.svg"
        "united kingdom", "ingiltere", "birlesik krallik" -> "uk.svg"
        "united states", "united states of america", "amerika birleşik devletleri" -> "United States of America.svg"
        "afghanistan" -> "afghanistan.svg"
        "albania" -> "albania.svg"
        "algeria" -> "algeria.svg"
        "american samoa" -> "American Samoa.svg"
        else -> null
    }
}

private fun RegionFlagFileNameByIso(
    iso: String
): String? {
    return when (iso) {
        "AF" -> "afghanistan.svg"
        "AX" -> "aland-islands.svg"
        "AL" -> "albania.svg"
        "DZ" -> "algeria.svg"
        "AS" -> "American Samoa.svg"
        "AD" -> "andorra.svg"
        "AO" -> "angola.svg"
        "AI" -> "anguilla.svg"
        "AG" -> "Antigua and Barbuda.svg"
        "AR" -> "argentina.svg"
        "AM" -> "armenia.svg"
        "AW" -> "aruba.svg"
        "AU" -> "australia.svg"
        "AT" -> "austria.svg"
        "AZ" -> "azerbaijan.svg"
        "BS" -> "bahamas.svg"
        "BH" -> "bahrain.svg"
        "BD" -> "bangladesh.svg"
        "BB" -> "barbados.svg"
        "BY" -> "belarus.svg"
        "BE" -> "belgium.svg"
        "BZ" -> "belize.svg"
        "BJ" -> "benin.svg"
        "BM" -> "bermuda.svg"
        "BT" -> "bhutan.svg"
        "BO" -> "bolivia.svg"
        "BQ" -> "bonaire.svg"
        "BA" -> "Bosnia and Herzegovina.svg"
        "BW" -> "botswana.svg"
        "BR" -> "brazil.svg"
        "VG" -> "British Virgin Islands.svg"
        "IO" -> "british-indian-ocean-territory.svg"
        "BN" -> "brunei.svg"
        "BG" -> "bulgaria.svg"
        "BF" -> "Burkina Faso.svg"
        "BI" -> "burundi.svg"
        "KH" -> "cambodia.svg"
        "CM" -> "cameroon.svg"
        "CA" -> "canada.svg"
        "CV" -> "Cape Verde.svg"
        "KY" -> "Cayman Islands.svg"
        "CF" -> "Central African Republic.svg"
        "TD" -> "chad.svg"
        "CL" -> "chile.svg"
        "CN" -> "china.svg"
        "CX" -> "Christmas Island.svg"
        "CC" -> "Cocos (Keeling) Islands.svg"
        "CO" -> "colombia.svg"
        "KM" -> "comoros.svg"
        "CG" -> "republic-of-the-congo.svg"
        "CD" -> "democratic-republic-of-congo.svg"
        "CK" -> "Cook Islands.svg"
        "CR" -> "Costa Rica.svg"
        "HR" -> "croatia.svg"
        "CU" -> "cuba.svg"
        "CW" -> "curacao.svg"
        "CZ" -> "Czech Republic.svg"
        "DK" -> "denmark.svg"
        "DJ" -> "djibouti.svg"
        "DM" -> "dominica.svg"
        "DO" -> "Dominican Republic.svg"
        "TL" -> "East Timor.svg"
        "EC" -> "ecuador.svg"
        "EG" -> "egypt.svg"
        "SV" -> "El Salvador.svg"
        "GB-ENG" -> "england.svg"
        "GQ" -> "Equatorial Guinea.svg"
        "ER" -> "eritrea.svg"
        "EE" -> "estonia.svg"
        "ET" -> "ethiopia.svg"
        "EU" -> "european-union.svg"
        "FK" -> "Falkland Islands (Malvinas).svg"
        "FO" -> "faroe-islands.svg"
        "FJ" -> "Fiji Islands.svg"
        "FI" -> "finland.svg"
        "FR" -> "france.svg"
        "PF" -> "French Polynesia.svg"
        "GA" -> "Republic of Gabon.svg"
        "GM" -> "gambia.svg"
        "GE" -> "georgia.svg"
        "DE" -> "germany.svg"
        "GH" -> "ghana.svg"
        "GI" -> "gibraltar.svg"
        "GR" -> "greece.svg"
        "GL" -> "greenland.svg"
        "GD" -> "grenada.svg"
        "GU" -> "guam.svg"
        "GT" -> "guatemala.svg"
        "GG" -> "guernsey.svg"
        "GW" -> "Guin_ea Bissau.svg"
        "GY" -> "Gui_ana.svg"
        "HT" -> "haiti.svg"
        "HN" -> "honduras.svg"
        "HK" -> "hong kong.svg"
        "HU" -> "hungary.svg"
        "IS" -> "iceland.svg"
        "IN" -> "india.svg"
        "ID" -> "indonesia.svg"
        "IR" -> "iran.svg"
        "IQ" -> "iraq.svg"
        "IE" -> "ireland.svg"
        "IM" -> "isle-of-man.svg"
        "IL" -> "israel.svg"
        "IT" -> "italy.svg"
        "CI" -> "ivory-coast.svg"
        "JM" -> "jamaica.svg"
        "JP" -> "japan.svg"
        "JE" -> "jersey.svg"
        "JO" -> "jordan.svg"
        "KZ" -> "kazakhistan.svg"
        "KE" -> "kenya.svg"
        "KI" -> "kiribati.svg"
        "XK" -> "kosovo.svg"
        "KW" -> "kuwait.svg"
        "KG" -> "kyrgyzstan.svg"
        "LA" -> "laos.svg"
        "LV" -> "latvia.svg"
        "LB" -> "lebanon.svg"
        "LS" -> "lesotho.svg"
        "LR" -> "liberia.svg"
        "LY" -> "libya.svg"
        "LI" -> "liechtenstein.svg"
        "LT" -> "lithuania.svg"
        "LU" -> "luxembourg.svg"
        "MO" -> "macau.svg"
        "MK" -> "republic-of-macedonia.svg"
        "MG" -> "madagascar.svg"
        "MW" -> "malawi.svg"
        "MY" -> "malaysia.svg"
        "MV" -> "maldives.svg"
        "ML" -> "Republic of Mali.svg"
        "MT" -> "malta.svg"
        "MH" -> "Marshall Islands.svg"
        "MQ" -> "martinique.svg"
        "MR" -> "mauritania.svg"
        "MU" -> "mauritius.svg"
        "MX" -> "mexico.svg"
        "FM" -> "micronesia.svg"
        "MD" -> "moldova.svg"
        "MC" -> "monaco.svg"
        "MN" -> "mongolia.svg"
        "ME" -> "montenegro.svg"
        "MS" -> "montserrat.svg"
        "MA" -> "morocco.svg"
        "MZ" -> "mozambique.svg"
        "MM" -> "myanmar.svg"
        "NA" -> "namibia.svg"
        "NR" -> "Nauru Island.svg"
        "NP" -> "nepal.svg"
        "NL" -> "netherlands.svg"
        "NZ" -> "New Zeland.svg"
        "NI" -> "nicaragua.svg"
        "NE" -> "niger.svg"
        "NG" -> "nigeria.svg"
        "NU" -> "niue.svg"
        "NF" -> "Norfolk Island.svg"
        "KP" -> "North Korea.svg"
        "MP" -> "Northern Mariana Islands.svg"
        "NO" -> "norway.svg"
        "OM" -> "oman.svg"
        "PK" -> "pakistan.svg"
        "PW" -> "palau.svg"
        "PS" -> "palestine.svg"
        "PA" -> "panama.svg"
        "PG" -> "papua-new-guinea.svg"
        "PY" -> "paraguay.svg"
        "PE" -> "peru.svg"
        "PH" -> "philippines.svg"
        "PN" -> "pitcairn-islands.svg"
        "PL" -> "poland.svg"
        "PR" -> "Porto Rico.svg"
        "PT" -> "portugal.svg"
        "QA" -> "qatar.svg"
        "RO" -> "romania.svg"
        "RU" -> "russia.svg"
        "RW" -> "Republic of Rwandese.svg"
        "WS" -> "samoa.svg"
        "SM" -> "San Marino.svg"
        "ST" -> "Sao Tome and Principe.svg"
        "SA" -> "saudi-arabia.svg"
        "GB-SCT" -> "scotland.svg"
        "SN" -> "senegal.svg"
        "RS" -> "serbia.svg"
        "SC" -> "Republic of Seychelles.svg"
        "SL" -> "Sierra Leone.svg"
        "SG" -> "singapore.svg"
        "SK" -> "slovakia.svg"
        "SI" -> "slovenia.svg"
        "SB" -> "Solomon Islands.svg"
        "SO" -> "Somalia Democratic Republic.svg"
        "ZA" -> "South Africa.svg"
        "KR" -> "south-korea.svg"
        "SS" -> "south-sudan.svg"
        "ES" -> "spain.svg"
        "LK" -> "Sri Lanka.svg"
        "SD" -> "sudan.svg"
        "SR" -> "suriname.svg"
        "SZ" -> "swaziland.svg"
        "SE" -> "sweden.svg"
        "CH" -> "switzerland.svg"
        "SY" -> "syria.svg"
        "TW" -> "taiwan.svg"
        "TJ" -> "tajikistan.svg"
        "TZ" -> "tanzania.svg"
        "TH" -> "thailand.svg"
        "TK" -> "tokelau.svg"
        "TO" -> "tonga.svg"
        "TT" -> "Trinidad & Tobago.svg"
        "TN" -> "tunisia.svg"
        "TR" -> "turkey.svg"
        "TM" -> "turkmenistan.svg"
        "TC" -> "Turks and Caicos Islands.svg"
        "TV" -> "tuvalu.svg"
        "UG" -> "uganda.svg"
        "UA" -> "ukraine.svg"
        "AE" -> "United Arab Emirates.svg"
        "GB", "UK" -> "uk.svg"
        "US", "USA" -> "United States of America.svg"
        "UY" -> "uruguay.svg"
        "UZ" -> "uzbekistan.svg"
        "VU" -> "vanuatu.svg"
        "VA" -> "Vatican.svg"
        "VE" -> "Venezuelan.svg"
        "VN" -> "vietnamese.svg"
        "VI" -> "virgin islands (ABD).svg"
        "YE" -> "yemen.svg"
        "ZM" -> "zambia.svg"
        "ZW" -> "zimbabwe.svg"
        else -> null
    }
}