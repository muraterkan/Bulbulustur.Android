package com.bulbulustur.android.Application.Views.Account

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeFields
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeState
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateAddressModel

@Composable
fun AccountAddressScreen(
    memberId: Int,
    addressCascadeState: AddressCascadeState,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onCountrySelected: (Int) -> Unit = {},
    onCountryStateSelected: (Int) -> Unit = {},
    onCountryDepartmentSelected: (Int?) -> Unit = {},
    onCitySelected: (Int) -> Unit = {},
    onDistrictSelected: (Int?) -> Unit = {},
    onSaveClick: (MemberUpdateAddressModel) -> Unit = {}
) {
    val selection = addressCascadeState.Selection

    val hasRequiredCountryState =
        !addressCascadeState.ShouldShowCountryState ||
                selection.CountryStateId > 0

    val hasRequiredCountryDepartment =
        !addressCascadeState.ShouldShowCountryDepartment ||
                selection.CountryDepartmentId != null &&
                selection.CountryDepartmentId > 0

    val canSubmit =
        memberId > 0 &&
                selection.CountryId > 0 &&
                hasRequiredCountryState &&
                hasRequiredCountryDepartment &&
                selection.CityId > 0 &&
                !addressCascadeState.IsLoading &&
                !isLoading

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "cbefdb0e-85b4-47f1-8d73-c281dc026149", fallback = "Ülke ve Şehir"),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(
                    PaddingValues(
                        start = BBSpacing.PageHorizontal,
                        top = BBSpacing.PageTopCompact,
                        end = BBSpacing.PageHorizontal,
                        bottom = BBSpacing.PageBottom
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGap)
        ) {
            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "3dfb92b9-34f2-4cb3-9337-10237d2fec0a", fallback = "Profilinizde kullanılacak ülke, şehir ve varsa yönetimsel bölge bilgilerini seçin."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                AddressCascadeFields(
                    state = addressCascadeState,
                    onCountrySelected = onCountrySelected,
                    onCountryStateSelected = onCountryStateSelected,
                    onCountryDepartmentSelected = onCountryDepartmentSelected,
                    onCitySelected = onCitySelected,
                    onDistrictSelected = onDistrictSelected,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                )
            }

            errorMessage
                ?.takeIf { it.isNotBlank() }
                ?.let { message ->
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

            BbButton(
                text = BBLocalization.Current.Get(key = "58104fd9-46c6-4304-9abb-07f5273a33f9", fallback = "Güncelle"),
                onClick = {
                    onSaveClick(
                        MemberUpdateAddressModel(
                            MemberId = memberId,
                            CountryId = selection.CountryId,
                            CountryStateId = selection.CountryStateId,
                            CountryDepartmentId = selection.CountryDepartmentId,
                            CityId = selection.CityId,
                            DistrictId = selection.DistrictId
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                enabled = canSubmit,
                isLoading = isLoading
            )
        }
    }
}
